package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.enums.SpendingCategory;
import com.codegym.finalModule.model.Customer;
import com.codegym.finalModule.model.CustomerReport;
import com.codegym.finalModule.model.Order;
import com.codegym.finalModule.model.OrderDetail;
import com.codegym.finalModule.repository.ICustomerReportRepository;
import com.codegym.finalModule.repository.ICustomerRepository;
import com.codegym.finalModule.repository.IOrderRepository;
import com.codegym.finalModule.service.interfaces.ICustomerReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerReportService implements ICustomerReportService {

    @Autowired
    private ICustomerReportRepository customerReportRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IOrderRepository orderRepository;

    @Value("${file.export.dir}")
    private String exportDir;


    @Transactional
    @Override
    public int updateCustomerReportsByDateRange(LocalDate fromDate, LocalDate toDate) {
        // Lặp qua từng ngày trong khoảng thời gian
        for (LocalDate date = fromDate; !date.isAfter(toDate); date = date.plusDays(1)) {
            List<Customer> customers = customerRepository.findAll();

            for (Customer customer : customers) {
                updateCustomerReportByDate(customer, date);
            }
        }
        return 0;
    }


    @Override
    public Page<CustomerReport> findAll(Pageable pageable) {
        return customerReportRepository.findAll(pageable);
    }

    public boolean updateCustomerReportByDate(Customer customer, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();

        List<Order> orders = orderRepository.findByCustomerAndCreateAtBetween(customer, startOfDay, endOfDay);
        if (orders.isEmpty()) {
            // Thêm thông báo lỗi khi không có dữ liệu trong ngày đó
            System.out.println("Không có dữ liệu đơn hàng cho khách hàng " + customer.getCustomerId() + " vào ngày " + date);
            return false;
        }

        double totalSpent = orders.stream().mapToDouble(Order::getTotalPrice).sum();
        int totalOrders = orders.size();
        int totalProductsPurchased = orders.stream()
                .flatMap(order -> order.getOrderDetails().stream())
                .mapToInt(OrderDetail::getQuantity)
                .sum();

        Order lastOrder = orders.stream()
                .max(Comparator.comparing(Order::getCreateAt))
                .orElse(null);

        CustomerReport report = customerReportRepository.findByCustomer_CustomerId(customer.getCustomerId())
                .orElseGet(() -> new CustomerReport(customer));

        report.setTotalOrders(totalOrders);
        report.setTotalSpent(totalSpent);
        report.setTotalProductsPurchased(totalProductsPurchased);
        report.setSpendingCategory(determineSpendingCategory(totalSpent, totalOrders));

        if (lastOrder != null) {
            report.setLastOrderDate(lastOrder.getCreateAt());

            // Lấy trạng thái từ Payment
            if (lastOrder.getPayment() != null) {
                report.setLastPaymentStatus(lastOrder.getPayment().getStatus());
            } else {
                report.setLastPaymentStatus(null);
            }
        }

        customerReportRepository.save(report);

        long totalCustomersToday = customerReportRepository.countDistinctCustomersByDate(startOfDay, endOfDay);
        System.out.println("Tổng số khách hàng trong ngày " + date + " là: " + totalCustomersToday);

        return true;
    }


    @Override
    public Page<CustomerReport> getAllCustomerReports(Pageable pageable) {
        return customerReportRepository.findAll(pageable);
    }


    @Override
    public CustomerReport getReportByCustomerId(Integer customerId) {
        return customerReportRepository.findByCustomer_CustomerId(customerId).orElse(null);
    }

    @Override
    public void deleteCustomerReport(List<Integer> reportId) {
        customerReportRepository.deleteAllById(reportId);
    }

    private SpendingCategory determineSpendingCategory(double totalSpent, int totalOrders) {
        if (totalSpent >= 50_000_000 && totalOrders >= 10) return SpendingCategory.VIP_PREMIUM;
        if (totalSpent >= 20_000_000 && totalOrders >= 5) return SpendingCategory.POTENTIAL;
        return SpendingCategory.REGULAR;
    }

    @Override
    public void editCustomerReport(Integer reportId, Integer totalOrders, Double totalSpent, Integer totalProductsPurchased) {
        Optional<CustomerReport> optionalReport = customerReportRepository.findById(reportId);

        if (optionalReport.isPresent()) {
            CustomerReport report = optionalReport.get();
            report.setTotalOrders(totalOrders);
            report.setTotalSpent(totalSpent);
            report.setTotalProductsPurchased(totalProductsPurchased);
            report.setSpendingCategory(determineSpendingCategory(totalSpent, totalOrders));

            customerReportRepository.save(report);
        }
    }

    @Override
    public Page<CustomerReport> findByDateBetween(LocalDate fromDate, LocalDate toDate, Pageable pageable) {
        return customerReportRepository.findReportsByDateRange(
                fromDate.atStartOfDay(),
                toDate.atTime(23, 59, 59),
                pageable
        );

    }

    @Override
    public void exportCustomerReportsToExcel(HttpServletResponse response) throws IOException {
        List<CustomerReport> reports = customerReportRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Customer Reports");

            // Tạo tiêu đề cột
            String[] headers = {"STT", "Tên khách hàng", "Tổng đơn hàng", "Tổng chi tiêu","Loại khách hàng ","Trạng thái thanh toán", "Ngày đơn hàng gần nhất"};
            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Điền dữ liệu vào từng dòng của file Excel
            int rowNum = 1;
            for (CustomerReport report : reports) {
                Row row = sheet.createRow(rowNum);

                row.createCell(0).setCellValue(rowNum);
                row.createCell(1).setCellValue(report.getCustomer().getCustomerName());
                row.createCell(2).setCellValue(report.getTotalOrders());
                row.createCell(3).setCellValue(report.getTotalSpent());
                row.createCell(5).setCellValue(report.getSpendingCategory() != null ? report.getSpendingCategory().toString() : "N/A");
                row.createCell(6).setCellValue(report.getLastPaymentStatus() != null ? report.getLastPaymentStatus().toString() : "N/A");
                row.createCell(7).setCellValue(report.getLastOrderDate() != null ? report.getLastOrderDate().toString() : "N/A");

                rowNum++;
            }

            // Ghi file vào response
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=customer_reports.xlsx");

            workbook.write(response.getOutputStream());
        }
    }


    @Override
    public List<CustomerReport> getReportsByDateRange(LocalDate fromDate, LocalDate toDate) {
        LocalDateTime startDate = fromDate.atStartOfDay();
        LocalDateTime endDate = toDate.atTime(23, 59, 59);
        return customerReportRepository.findReportsByDateRange(startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerReport> getCustomerReportsByDateRange(LocalDate fromDate, LocalDate toDate, Pageable pageable) {
        LocalDateTime startDate = fromDate.atStartOfDay();
        LocalDateTime endDate = toDate.atTime(23, 59, 59); // Lấy hết ngày kết thúc
        return customerReportRepository.findReportsByDateRange(startDate, endDate, pageable);
    }
    public long getTotalCustomersByDateRange(LocalDate fromDate, LocalDate toDate) {
        LocalDateTime startOfDay = fromDate.atStartOfDay();
        LocalDateTime endOfDay = toDate.plusDays(1).atStartOfDay();
        return customerReportRepository.countTotalCustomersByDateRange(startOfDay, endOfDay);
    }

}
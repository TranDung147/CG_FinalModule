package com.codegym.finalModule.controller.Report;

import com.codegym.finalModule.model.CustomerReport;
import com.codegym.finalModule.service.impl.CustomerReportService;
import com.codegym.finalModule.service.interfaces.ICustomerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/Admin/report")
public class CustomerReportController {
    @Autowired
    private CustomerReportService customerReportService;
    @Autowired
    private ICustomerService customerService;



    @GetMapping
    public String listReports(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

        Page<CustomerReport> reports = Page.empty(); // Mặc định không có dữ liệu hiển thị

        if (fromDate != null && toDate != null) {
            reports = customerReportService.getCustomerReportsByDateRange(fromDate, toDate, PageRequest.of(page, 10));
        }
        model.addAttribute("report", reports);
        model.addAttribute("customerReports", reports);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);

        return "admin/customer_report/customer_report_list";
    }

    @PostMapping("/addRange")
    public String updateReportsByDateRange(
            @RequestParam("fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam("toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            RedirectAttributes redirectAttributes,
            Model model) { // Thêm model vào đây

        if (fromDate.isAfter(toDate)) {
            redirectAttributes.addFlashAttribute("message", "❌ Ngày bắt đầu không thể lớn hơn ngày kết thúc.");
            return "redirect:/Admin/report";
        }

        customerReportService.updateCustomerReportsByDateRange(fromDate, toDate);

        Page<CustomerReport> reports = customerReportService.getCustomerReportsByDateRange(fromDate, toDate, PageRequest.of(0, 10));
        long totalCustomers = 0;

        if (fromDate != null && toDate != null) {
            int page = 0;
            reports = customerReportService.getCustomerReportsByDateRange(fromDate, toDate, PageRequest.of(page, 10));

            try {
                totalCustomers = customerReportService.getTotalCustomersByDateRange(fromDate, toDate);
            } catch (Exception e) {
                System.out.println("❌ Lỗi khi lấy tổng số khách hàng: " + e.getMessage());
                totalCustomers = 0;
            }
        }

        // Thêm dữ liệu vào model để hiển thị
        model.addAttribute("customerReports", reports);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("totalCustomers", totalCustomers);

        redirectAttributes.addFlashAttribute("message", "✅ Cập nhật báo cáo từ " + fromDate + " đến " + toDate + " thành công!");

        return "admin/customer_report/customer_report_list"; // Trả về trực tiếp view thay vì redirect
    }


    @PostMapping("/deleteAll")
    public String deleteCustomerReport(@RequestParam(name = "selectedCustomers", required = false) List<Integer> reportIds,
                                       RedirectAttributes redirectAttributes) {
        if (reportIds == null || reportIds.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Vui lòng chọn ít nhất một báo cáo để xóa.");
        } else {
            customerReportService.deleteCustomerReport(reportIds);
            redirectAttributes.addFlashAttribute("message", "Đã xóa thành công " + reportIds.size() + " báo cáo.");
        }
        return "redirect:/Admin/report"; // Redirect lại trang
    }
    @PostMapping("/edit")
    public String editCustomerReport(@RequestParam("reportId") Integer reportId,
                                     @RequestParam("totalOrders") Integer totalOrders,
                                     @RequestParam("totalSpent") Double totalSpent,
                                     @RequestParam("totalProductsPurchased") Integer totalProductsPurchased,
                                     RedirectAttributes redirectAttributes) {
        customerReportService.editCustomerReport(reportId, totalOrders, totalSpent, totalProductsPurchased);
        redirectAttributes.addFlashAttribute("message", "Cập nhật báo cáo thành công!");
        return "redirect:/Admin/report";
    }

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        customerReportService.exportCustomerReportsToExcel(response);
    }

}

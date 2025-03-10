package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.DTO.order.OrderDTO;
import com.codegym.finalModule.DTO.order.ProductOrderChoiceDTO;
import com.codegym.finalModule.DTO.order.ProductOrderDTO;
import com.codegym.finalModule.DTO.product.ProductDTO;
import com.codegym.finalModule.enums.OrderStatus;
import com.codegym.finalModule.model.Customer;
import com.codegym.finalModule.model.Order;
import com.codegym.finalModule.model.OrderDetail;
import com.codegym.finalModule.model.Product;
import com.codegym.finalModule.repository.ICustomerRepository;
import com.codegym.finalModule.repository.IOrderDetailRepository;
import com.codegym.finalModule.repository.IOrderRepository;
import com.codegym.finalModule.repository.IProductRepository;
import com.codegym.finalModule.service.interfaces.IOrderService;
import com.codegym.finalModule.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    CustomerService customerService;
//Sau move to customer
    @Autowired
    ICustomerRepository customerRepository;

    @Autowired
    IOrderRepository orderRepository;

    @Autowired
    IOrderDetailRepository orderDetailRepository;

    @Autowired
    IProductService productService;
//Sau move to product
    @Autowired
    IProductRepository productRepository;


    @Override
    public void saveOrder(OrderDTO orderDTO) {

        Order order= new Order();

        //customer
        Integer customerID = orderDTO.getCustomerId();
        Customer customer = customerService.getCustomerById(customerID);
        order.setCustomer(customer);

        order.setTotalPrice(0.00);
        order.setStatus(OrderStatus.PENDING);
        order.setCreateAt(LocalDateTime.now());
        order.setUpdateAt(LocalDateTime.now());
        Order saveOrder = orderRepository.save(order);

        Integer orderID = saveOrder.getOrderID();

        //orderDetails
        for ( ProductOrderDTO productOrderDTO : orderDTO.getProductOrderDTOList()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(saveOrder);
            orderDetail.setProduct(productService.findById(productOrderDTO.getProductId()));
            orderDetail.setQuantity(productOrderDTO.getQuantity());
            orderDetail.setPrice((double)productOrderDTO.getPriceIndex());
            saveOrderDetail(orderDetail);
        }

    }

    public void saveOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public Page<CustomerDTO> getAllCustomersDTO(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.map(this::convertToDTO);
    }

    @Override
    public Page<CustomerDTO> searchCustomers(String keyword, String filter, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size); // Trang trong Spring bắt đầu từ 0
        Page<Customer> customers;

        switch (filter) {
            case "name":
                customers = customerRepository.findByCustomerNameContaining(keyword, pageable);
                break;
            case "phone":
                customers = customerRepository.findByPhoneNumberContaining(keyword, pageable);
                break;
            case "address":
                customers = customerRepository.findByAddressContaining(keyword, pageable);
                break;
            case "email":
                customers = customerRepository.searchByEmail(keyword, pageable);
                break;
            default:
                customers = customerRepository.findAll(pageable);
        }

        return customers.map(this::convertToDTO);
    }

    private CustomerDTO convertToDTO(Customer customer) {
        return new CustomerDTO(
                customer.getCustomerId(),
                customer.getCustomerName(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                customer.getBirthDate(),
                customer.getUser().getEmail()
        );
    }

}

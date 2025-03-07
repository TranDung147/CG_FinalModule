package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.exception.customer.CustomerError;
import com.codegym.finalModule.exception.customer.CustomerException;
import com.codegym.finalModule.mapper.customer.CustomerMapper;
import com.codegym.finalModule.model.Customer;
import com.codegym.finalModule.repository.ICustomerRepository;
import com.codegym.finalModule.repository.IUserRepository;
import com.codegym.finalModule.service.interfaces.ICustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService <Customer , CustomerDTO> {
   private final ICustomerRepository customerRepository;
   private final CustomerMapper customerMapper;
   private final IUserRepository userRepository;
   public CustomerService(ICustomerRepository customerRepository ,
                          CustomerMapper customerMapper ,
                          IUserRepository userRepository) {
       this.customerRepository = customerRepository;
       this.customerMapper = customerMapper;
       this.userRepository = userRepository;
   }

    @Override
    public Page<Customer> getAllCustomers(int page, int size) {
        return this.customerRepository.findAll(PageRequest.of(page - 1, size));
    }

    @Override
    public Page<Customer> searchByFieldAndKey(String field, String keyword , int page , int size) throws NumberFormatException{
        Pageable pageable = PageRequest.of(page - 1, size);
        if ("id".equals(field)) {
                int id = Integer.parseInt(keyword);
                return this.customerRepository.findByCustomerId(id , pageable) ;
        }
        return this.customerRepository.searchCustomers(field, keyword, pageable);
    }

    @Override
    public Customer getCustomerById(int id) {
        return this.customerRepository.findById(id).orElseThrow(
                () -> new CustomerException(CustomerError.CUSTOMER_NOTFOUND));
    }

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        if (this.customerRepository.existsByPhoneNumber(customerDTO.getPhone())) {
            throw new CustomerException(CustomerError.INVALID_PHONE_NUMBER);
        }
        this.customerRepository.save(this.customerMapper.convertToCustomer(customerDTO)) ;
    }


    @Override
    public void deleteCustomer(List<Integer> customers) {
        customerRepository.deleteAllById(customers);
    }


    @Override
    public void updateCustomer(CustomerDTO customerDTO, int id ) {
        Customer customer = this.customerRepository.findById(id).orElseThrow(
                () -> new CustomerException(CustomerError.CUSTOMER_NOTFOUND)
        );
        if (!customer.getPhoneNumber().equals(customerDTO.getPhone())
                && this.customerRepository.existsByPhoneNumber(customerDTO.getPhone())) {
            throw new CustomerException(CustomerError.INVALID_PHONE_NUMBER);
        }
        if (!customerDTO.getEmail().equals(customer.getUser().getEmail())
                && this.userRepository.existsByEmail(customerDTO.getEmail())) {
            throw new CustomerException(CustomerError.INVALID_EMAIL);
        }
        customer.getUser().setEmail(customerDTO.getEmail());
        this.userRepository.save(customer.getUser());
        customer.setCustomerName(customerDTO.getFullName());
        customer.setAddress(customerDTO.getAddress());
        customer.setPhoneNumber(customerDTO.getPhone());
        customer.setBirthDate(customerDTO.getBirthDate());
        this.customerRepository.save(customer);
    }
    @Override
    public CustomerDTO findCustomerDTOById(int id) {
       Customer customer = this.customerRepository.findById(id).orElseThrow(
               () -> new CustomerException(CustomerError.CUSTOMER_NOTFOUND)
       );
        return  this.customerMapper.convertToCustomerDTO(customer);
    }

    @Override
    public List<CustomerDTO> getCustomersByKeyword(String keyword) {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        customerDTOS.add(this.findCustomerDTOById(13));
        customerDTOS.add(this.findCustomerDTOById(14));
        return customerDTOS;

    }

    @Override
    public List<CustomerDTO> getAllCustomersDTO() {
       List<Customer> customers = customerRepository.findAll();
       return customers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> searchCustomers(String keyword, String filter) {
        List<Customer> customers;

        if (keyword != null && !keyword.isEmpty()) {
            customers = switch (filter) {
                case "name" -> customerRepository.findByCustomerNameContaining(keyword);
                case "phone" -> customerRepository.findByPhoneNumberContaining(keyword);
                case "address" -> customerRepository.findByAddressContaining(keyword);
                case "email" -> customerRepository.searchByEmail(keyword);
                default -> customerRepository.findAll();
            };
        } else {
            customers = customerRepository.findAll();
        }

        // Chuyển danh sách Customer thành CustomerDTO
        return customers.stream().map(CustomerDTO::new).collect(Collectors.toList());
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


    public Integer addCustomerAndGetId(CustomerDTO customerDTO) {
        if (this.customerRepository.existsByPhoneNumber(customerDTO.getPhone())) {
            throw new CustomerException(CustomerError.INVALID_PHONE_NUMBER);
        }
        this.customerRepository.save(this.customerMapper.convertToCustomer(customerDTO)) ;
        return this.customerRepository.findByPhoneNumber(customerDTO.getPhone()).getCustomerId();
    }


}

package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.exception.customer.CustomerError;
import com.codegym.finalModule.exception.customer.CustomerException;
import com.codegym.finalModule.mapper.customer.CustomerMapper;
import com.codegym.finalModule.model.Customer;
import com.codegym.finalModule.repository.ICustomerRepository;
import com.codegym.finalModule.repository.IUserRepository;
import com.codegym.finalModule.service.interfaces.ICustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        if (this.customerRepository.existsByPhoneNumber(customerDTO.getPhoneNumber())) {
            throw new CustomerException(CustomerError.INVALID_PHONE_NUMBER);
        }
        this.customerRepository.save(this.customerMapper.convertToCustomer(customerDTO)) ;
    }


    @Override
    public void updateCustomer(CustomerDTO customerDTO, int id ) {
        Customer customer = this.customerRepository.findById(id).orElseThrow(
                () -> new CustomerException(CustomerError.CUSTOMER_NOTFOUND)
        );
        if (!customer.getPhoneNumber().equals(customerDTO.getPhoneNumber())
                && this.customerRepository.existsByPhoneNumber(customerDTO.getPhoneNumber())) {
            throw new CustomerException(CustomerError.INVALID_PHONE_NUMBER);
        }
        if (!customerDTO.getEmail().equals(customer.getEmail())
                && this.userRepository.existsByEmail(customerDTO.getEmail())) {
            throw new CustomerException(CustomerError.INVALID_EMAIL);
        }
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setAddress(customerDTO.getAddress());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setEmail(customerDTO.getEmail());
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
    public Optional<Customer> getCustomerByCustomerId(Integer customerId) {
        return customerRepository.findById(customerId);
    }


    public Integer addCustomerAndGetId(CustomerDTO customerDTO) {
        if (this.customerRepository.existsByPhoneNumber(customerDTO.getPhoneNumber())) {
            throw new CustomerException(CustomerError.INVALID_PHONE_NUMBER);
        }
        this.customerRepository.save(this.customerMapper.convertToCustomer(customerDTO)) ;
        return this.customerRepository.findByPhoneNumber(customerDTO.getPhoneNumber()).getCustomerId();
    }

    


    @Override
    public Page<Customer> searchCustomers(String keyword, String filter, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Customer> customers;

        switch (filter) {
            case "name":
                customers = customerRepository.findByCustomerNameContainingIgnoreCase(keyword, pageable);
                break;
            case "phone":
                customers = customerRepository.findByPhoneNumberContaining(keyword, pageable);
                break;
            case "address":
                customers = customerRepository.findByAddressContainingIgnoreCase(keyword, pageable);
                break;
            case "email":
                customers = customerRepository.findByEmailContainingIgnoreCase(keyword, pageable);
                break;
            default:
                customers = customerRepository.findAll(pageable);
        }

        return customers;
    }

}

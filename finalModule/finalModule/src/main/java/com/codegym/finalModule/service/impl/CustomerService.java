package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.dto.customer.CustomerDTO;
import com.codegym.finalModule.exception.customer.CustomerError;
import com.codegym.finalModule.exception.customer.CustomerException;
import com.codegym.finalModule.mapper.CustomerMapper;
import com.codegym.finalModule.model.Customer;
import com.codegym.finalModule.repository.ICustomerRepository;
import com.codegym.finalModule.service.interfaces.ICustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService <Customer , CustomerDTO> {
   private final ICustomerRepository customerRepository;
   private final CustomerMapper customerMapper;
   public CustomerService(ICustomerRepository customerRepository ,
                          CustomerMapper customerMapper) {
       this.customerRepository = customerRepository;
       this.customerMapper = customerMapper;
   }

    @Override
    public Page<Customer> getAllCustomers(int page, int size) {
        return this.customerRepository.findAll(PageRequest.of(page - 1, size));
    }

    @Override
    public Page<Customer> searchByFieldAndKey(String field, String keyword , int page , int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        if ("id".equals(field)) {
            try {
                int id = Integer.parseInt(keyword);
                return this.customerRepository.findByCustomerId(id , pageable) ;
            }catch (NumberFormatException e) {
                throw new NumberFormatException("Not valid !") ;
            }
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
        this.customerRepository.save(this.customerMapper.convertToCustomerToCustomer(customerDTO)) ;
    }

    @Override
    public void deleteCustomer(int id) {
        if (this.customerRepository.existsById(id)) {
            this.customerRepository.deleteById(id);
        }else {
            throw new CustomerException(CustomerError.ID_NOTFOUND) ;
        }
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO, int id) {
        if (this.customerRepository.existsByPhoneNumber(customerDTO.getPhone())) {
            throw new CustomerException(CustomerError.INVALID_PHONE_NUMBER);
        }
        Customer customer = this.customerRepository.findById(id).orElseThrow(
                () -> new CustomerException(CustomerError.CUSTOMER_NOTFOUND)
        );
        customer.setCustomerName(customer.getCustomerName());
        customer.setAddress(customerDTO.getAddress());
        customer.setPhoneNumber(customer.getPhoneNumber());
        customer.setBirthDate(customer.getBirthDate());
        this.customerRepository.save(customer);
    }
}

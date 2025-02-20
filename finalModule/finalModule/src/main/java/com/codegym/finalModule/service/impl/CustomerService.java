package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.exception.customer.CustomerError;
import com.codegym.finalModule.exception.customer.CustomerException;
import com.codegym.finalModule.mapper.customer.CustomerMapper;
import com.codegym.finalModule.model.Customer;
import com.codegym.finalModule.repository.ICustomerRepository;
import com.codegym.finalModule.service.interfaces.ICustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void deleteCustomer(int id) {
        if (this.customerRepository.existsById(id)) {
            this.customerRepository.deleteById(id);
        }else {
            throw new CustomerException(CustomerError.ID_NOTFOUND) ;
        }
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
        customer.setCustomerName(customerDTO.getFullName());
        customer.setAddress(customerDTO.getAddress());
        customer.setPhoneNumber(customerDTO.getPhone());
        customer.setBirthDate(customerDTO.getBirthDate());
        this.customerRepository.save(customer);
    }

    @Override
    public void deleteAllCustomersById(List<Integer> ids) {
        this.customerRepository.deleteAllById(ids);
    }

    @Override
    public CustomerDTO findCustomerDTOById(int id) {
       Customer customer = this.customerRepository.findById(id).orElseThrow(
               () -> new CustomerException(CustomerError.CUSTOMER_NOTFOUND)
       );
        return  this.customerMapper.convertToCustomerDTO(customer);
    }
}

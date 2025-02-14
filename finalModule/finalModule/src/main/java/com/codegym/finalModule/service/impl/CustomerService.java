package com.codegym.finalModule.service.impl;

import com.codegym.finalModule.dto.customer.CustomerDTO;
import com.codegym.finalModule.exception.customer.CustomerError;
import com.codegym.finalModule.exception.customer.CustomerException;
import com.codegym.finalModule.mapper.CustomerMapper;
import com.codegym.finalModule.model.Customer;
import com.codegym.finalModule.repository.ICustomerRepository;
import com.codegym.finalModule.service.interfaces.ICustomerService;

import java.util.List;

public class CustomerService implements ICustomerService <Customer , CustomerDTO> {
   private final ICustomerRepository customerRepository;
   private final CustomerMapper customerMapper;
   public CustomerService(ICustomerRepository customerRepository,
                          CustomerMapper customerMapper) {
       this.customerRepository = customerRepository;
       this.customerMapper = customerMapper;
   }

    @Override
    public List<Customer> getAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer getById(int id) {
        return this.customerRepository.findById(id).orElseThrow(
                () -> new CustomerException(CustomerError.CUSTOMER_NOTFOUND));
    }

    @Override
    public void delete(int id) {
        if (this.customerRepository.existsById(id)) {
            this.customerRepository.deleteById(id);
        }else {
            throw new CustomerException(CustomerError.ID_NOTFOUND) ;
        }
    }

    @Override
    public void update(CustomerDTO customerDTO, int id) {
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

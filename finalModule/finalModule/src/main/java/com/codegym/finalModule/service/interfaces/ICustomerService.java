package com.codegym.finalModule.service.interfaces;

import com.codegym.finalModule.DTO.customer.CustomerDTO;
import com.codegym.finalModule.model.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerService <E,D>{
    Page<E> getAllCustomers(int page, int size);
    Page<E> searchByFieldAndKey(String field, String keyword , int page, int size);
    E getCustomerById(int id);
    void saveCustomer(D d);
    void deleteCustomer(List<Integer> customers);
    void updateCustomer(D d , int id);
    D findCustomerDTOById(int id);
    List<CustomerDTO> getCustomersByKeyword(String keyword);
}

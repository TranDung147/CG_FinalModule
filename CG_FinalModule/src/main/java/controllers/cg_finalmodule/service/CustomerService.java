package controllers.cg_finalmodule.service;


import controllers.cg_finalmodule.dto.CustomerRequest;
import controllers.cg_finalmodule.exception.customer.CustomerException;
import controllers.cg_finalmodule.exception.customer.ErrorCustomer;
import controllers.cg_finalmodule.mapper.CustomerMapper;
import controllers.cg_finalmodule.model.Customer;
import controllers.cg_finalmodule.repository.ICustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final ICustomerRepository iCustomerRepository;
    public CustomerService(CustomerMapper customerMapper , ICustomerRepository iCustomerRepository) {
        this.customerMapper = customerMapper;
        this.iCustomerRepository = iCustomerRepository;
    }

    public List<Customer> getAllCustomers(Pageable pageable , Specification<Customer> specification) {
        Page<Customer> customerPage = this.iCustomerRepository.findAll(pageable , specification);
        return customerPage.getContent();
    }
    public Customer getCustomerById(int id) {
        return this.iCustomerRepository.findById(id).orElseThrow(()
                -> new CustomerException(ErrorCustomer.CUSTOMER_NOT_FOUND));
    }
    public Customer createCustomer(CustomerRequest customerRq) {
        Customer customer = this.customerMapper.convertToCustomer(customerRq) ;
        return this.iCustomerRepository.save(customer);
    }
    public Customer updateCustomer(int id, CustomerRequest customerRq) {
        Customer customer = this.iCustomerRepository.findById(id).orElseThrow(()
                -> new CustomerException(ErrorCustomer.CUSTOMER_NOT_FOUND));
        customer.setName(customerRq.getName());
        customer.setEmail(customerRq.getEmail());
        customer.setPhone(customerRq.getPhone());
        customer.setBirthDate(customerRq.getBirthDate());
        return this.iCustomerRepository.save(customer);
    }
    public void deleteCustomer(int id) {
        if (this.iCustomerRepository.existsById(id)) {
            this.iCustomerRepository.deleteById(id);
        }else {
            throw new CustomerException(ErrorCustomer.CUSTOMER_NOT_FOUND);
        }

    }

}

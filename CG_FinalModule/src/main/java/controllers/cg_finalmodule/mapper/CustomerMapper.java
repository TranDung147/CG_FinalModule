package controllers.cg_finalmodule.mapper;


import controllers.cg_finalmodule.dto.CustomerRequest;
import controllers.cg_finalmodule.model.Customer;
import controllers.cg_finalmodule.repository.ICustomerRepository;
import org.springframework.stereotype.Component;


@Component
public class CustomerMapper {
    private final ICustomerRepository iCustomerRepository;
    public CustomerMapper(ICustomerRepository iCustomerRepository) {
        this.iCustomerRepository = iCustomerRepository;
    }
    public Customer convertToCustomer(CustomerRequest customerRequest) {
        if (iCustomerRepository.existsByEmail(customerRequest.getEmail())) {
            throw new RuntimeException("Customer with email " + customerRequest.getEmail() + " already exists");
        }
        return Customer.builder()
                .name(customerRequest.getName())
                .email(customerRequest.getEmail())
                .phone(customerRequest.getPhone())
                .birthDate(customerRequest.getBirthDate())
                .build();
    }
}

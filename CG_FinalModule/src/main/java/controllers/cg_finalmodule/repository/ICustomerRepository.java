package controllers.cg_finalmodule.repository;

import controllers.cg_finalmodule.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    Boolean existsByEmail(String email);
//    Page<Customer> findAll(Pageable pageable , Specification<Customer> specification);
}

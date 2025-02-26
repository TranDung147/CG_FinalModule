package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBrandRepository extends JpaRepository<Brand, Integer> {
    List<Brand> findByNameContainingIgnoreCase(String name);
}

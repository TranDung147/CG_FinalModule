package com.codegym.finalModule.repository;

import com.codegym.finalModule.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBrandRepository extends JpaRepository<Brand, Integer> {
}

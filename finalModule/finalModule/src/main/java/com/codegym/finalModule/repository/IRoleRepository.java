package com.codegym.finalModule.repository;


import com.codegym.finalModule.enums.RoleEnums;
import com.codegym.finalModule.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {
   Role findByRoleName (RoleEnums roleEnums) ;
}

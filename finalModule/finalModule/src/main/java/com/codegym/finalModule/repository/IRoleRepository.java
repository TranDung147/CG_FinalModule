package com.codegym.finalModule.repository;


import com.codegym.finalModule.enums.RoleEnums;
import com.codegym.finalModule.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {
   Role findByRoleName (RoleEnums roleEnums) ;
   Optional<Role> findByRoleName(String roleName);
}

package com.geneinsure.cqmservice.repository;

import com.geneinsure.cqmservice.model.entity.Role;
import com.geneinsure.cqmservice.model.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Chirinda Nyasha 26/11/2021
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByName(RoleType roleName);
}

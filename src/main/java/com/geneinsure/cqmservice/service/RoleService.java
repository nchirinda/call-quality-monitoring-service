package com.geneinsure.cqmservice.service;

import com.geneinsure.cqmservice.model.entity.Role;
import com.geneinsure.cqmservice.model.enums.RoleType;

import java.util.Optional;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

public interface RoleService {
    Optional<Role> findRoleByName(RoleType roleName);
}

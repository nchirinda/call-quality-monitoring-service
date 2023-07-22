package com.geneinsure.cqmservice.service;

import com.geneinsure.cqmservice.model.entity.Role;
import com.geneinsure.cqmservice.model.enums.RoleType;
import com.geneinsure.cqmservice.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@Transactional
@Service
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {

    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    protected JpaRepository<Role, String> getRepository() {
        return roleRepository;
    }

    @Override
    public Optional<Role> findRoleByName(RoleType roleName) {
        return roleRepository.findByName(roleName);
    }
}

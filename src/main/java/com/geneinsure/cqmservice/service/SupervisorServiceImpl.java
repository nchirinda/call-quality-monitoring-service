package com.geneinsure.cqmservice.service;

import com.geneinsure.cqmservice.model.entity.Supervisor;
import com.geneinsure.cqmservice.repository.SupervisorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@Transactional
@Service
public class SupervisorServiceImpl extends AbstractService<Supervisor> implements SupervisorService {

    private static final Logger log = LoggerFactory.getLogger(SupervisorServiceImpl.class);

    private final SupervisorRepository supervisorRepository;

    @Autowired
    public SupervisorServiceImpl(SupervisorRepository supervisorRepository) {
        this.supervisorRepository = supervisorRepository;
    }

    @Override
    protected JpaRepository<Supervisor, String> getRepository() {
        return supervisorRepository;
    }
}

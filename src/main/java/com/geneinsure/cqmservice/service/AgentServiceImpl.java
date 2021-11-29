package com.geneinsure.cqmservice.service;

import com.geneinsure.cqmservice.model.entity.Agent;
import com.geneinsure.cqmservice.repository.AgentRepository;
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
public class AgentServiceImpl extends AbstractService<Agent> implements AgentService {

    private static final Logger log = LoggerFactory.getLogger(AgentServiceImpl.class);

    private final AgentRepository agentRepository;

    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    protected JpaRepository<Agent, String> getRepository() {
        return agentRepository;
    }
}

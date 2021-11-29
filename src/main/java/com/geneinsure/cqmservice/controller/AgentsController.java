package com.geneinsure.cqmservice.controller;

import com.geneinsure.cqmservice.model.entity.Agent;
import com.geneinsure.cqmservice.service.AbstractService;
import com.geneinsure.cqmservice.service.AgentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@RestController
@RequestMapping(path = "agents")
public class AgentsController extends AbstractController<Agent> {

    private final AgentServiceImpl agentService;

    @Autowired
    public AgentsController(AgentServiceImpl agentService) {
        this.agentService = agentService;
    }

    @Override
    protected AbstractService<Agent> getService() {
        return agentService;
    }
}

package com.geneinsure.cqmservice.controller;

import com.geneinsure.cqmservice.model.entity.Supervisor;
import com.geneinsure.cqmservice.service.AbstractService;
import com.geneinsure.cqmservice.service.SupervisorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@RestController
@RequestMapping(path = "supervisors")
public class SupervisorsController extends AbstractController<Supervisor> {

    private final SupervisorServiceImpl supervisorService;

    @Autowired
    public SupervisorsController(SupervisorServiceImpl supervisorService) {
        this.supervisorService = supervisorService;
    }

    @Override
    protected AbstractService<Supervisor> getService() {
        return supervisorService;
    }
}

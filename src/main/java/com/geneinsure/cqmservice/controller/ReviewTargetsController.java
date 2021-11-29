package com.geneinsure.cqmservice.controller;

import com.geneinsure.cqmservice.model.entity.ReviewTarget;
import com.geneinsure.cqmservice.service.AbstractService;
import com.geneinsure.cqmservice.service.ReviewTargetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@RestController
@RequestMapping(path = "review_targets")
public class ReviewTargetsController extends AbstractController<ReviewTarget> {

    private final ReviewTargetServiceImpl reviewTargetService;

    @Autowired
    public ReviewTargetsController(ReviewTargetServiceImpl reviewTargetService) {
        this.reviewTargetService = reviewTargetService;
    }

    @Override
    protected AbstractService<ReviewTarget> getService() {
        return reviewTargetService;
    }

}

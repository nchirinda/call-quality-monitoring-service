package com.geneinsure.cqmservice.service;

import com.geneinsure.cqmservice.model.entity.ReviewTarget;
import com.geneinsure.cqmservice.repository.ReviewTargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@Transactional
@Service
public class ReviewTargetServiceImpl extends AbstractService<ReviewTarget> implements ReviewTargetService {

    private final ReviewTargetRepository ReviewTargetRepository;

    @Autowired
    public ReviewTargetServiceImpl(ReviewTargetRepository ReviewTargetRepository) {
        this.ReviewTargetRepository = ReviewTargetRepository;
    }

    @Override
    protected JpaRepository<ReviewTarget, String> getRepository() {
        return ReviewTargetRepository;
    }
}

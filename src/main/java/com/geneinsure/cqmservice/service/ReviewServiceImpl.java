package com.geneinsure.cqmservice.service;

import com.geneinsure.cqmservice.model.entity.Review;
import com.geneinsure.cqmservice.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@Transactional
@Service
public class ReviewServiceImpl extends AbstractService<Review> implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    protected JpaRepository<Review, String> getRepository() {
        return reviewRepository;
    }

    @Override
    public List<Review> findUserReviews(String userId) {
        return reviewRepository.findAllByCustomerUserId(userId);
    }
}

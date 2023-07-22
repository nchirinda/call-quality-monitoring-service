package com.geneinsure.cqmservice.service;

import com.geneinsure.cqmservice.model.entity.Review;

import java.util.List;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

public interface ReviewService {

    List<Review> findUserReviews(String userId);
}

package com.geneinsure.cqmservice.controller;

import com.geneinsure.cqmservice.controller.payload.ApiResp;
import com.geneinsure.cqmservice.exception.ResourceNotFoundException;
import com.geneinsure.cqmservice.model.entity.Review;
import com.geneinsure.cqmservice.service.AbstractService;
import com.geneinsure.cqmservice.service.ReviewServiceImpl;
import com.geneinsure.cqmservice.util.Formatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@RestController
@RequestMapping(path = "reviews")
public class ReviewsController extends AbstractController<Review> {

    private static final Logger log = LoggerFactory.getLogger(ReviewsController.class);

    private final ReviewServiceImpl reviewService;

    @Autowired
    public ReviewsController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    protected AbstractService<Review> getService() {
        return reviewService;
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<?> getUserReviews(@PathVariable("user_id") String userId) {
        List<Review> customerReviews = reviewService.findUserReviews(userId);
        if (customerReviews.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(customerReviews);
    }

    // Create a new Resource
    @Override
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Review newReview) {
        log.info("Creating Review - {}", Formatter.toJson(newReview));

        Review createdReview = reviewService.save(newReview);

        URI resourceLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdReview.getId())
                .toUri();

        return ResponseEntity.created(resourceLocation).body(new ApiResp(createdReview.getId()));
    }
}

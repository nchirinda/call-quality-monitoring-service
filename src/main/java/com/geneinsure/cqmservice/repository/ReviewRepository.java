package com.geneinsure.cqmservice.repository;

import com.geneinsure.cqmservice.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Chirinda Nyasha 26/11/2021
 */

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    List<Review> findAllByCustomerUserId(String id);
}

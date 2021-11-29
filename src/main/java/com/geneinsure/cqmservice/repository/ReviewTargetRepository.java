package com.geneinsure.cqmservice.repository;

import com.geneinsure.cqmservice.model.entity.ReviewTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Chirinda Nyasha 26/11/2021
 */

@Repository
public interface ReviewTargetRepository extends JpaRepository<ReviewTarget, String> {
}

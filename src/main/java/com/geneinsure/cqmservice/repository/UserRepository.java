package com.geneinsure.cqmservice.repository;

import com.geneinsure.cqmservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Chirinda Nyasha 26/11/2021
 */

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByContactDetailEmailAddress(String emailAddress);
}

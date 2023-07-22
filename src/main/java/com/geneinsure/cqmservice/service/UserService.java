package com.geneinsure.cqmservice.service;

import com.geneinsure.cqmservice.controller.payload.LoginReq;
import com.geneinsure.cqmservice.model.entity.User;

import java.util.Optional;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

public interface UserService {

    Optional<User> authenticateUser(LoginReq loginReq);

    Optional<User> findUserByEmailAddress(String emailAddress);
}

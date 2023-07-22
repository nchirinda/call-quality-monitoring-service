package com.geneinsure.cqmservice.service;

import com.geneinsure.cqmservice.controller.payload.LoginReq;
import com.geneinsure.cqmservice.model.entity.User;
import com.geneinsure.cqmservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@Transactional
@Service
public class UserServiceImpl extends AbstractService<User> implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected JpaRepository<User, String> getRepository() {
        return userRepository;
    }

    @Override
    public Optional<User> authenticateUser(LoginReq loginReq) {
        return findUserByEmailAddress(loginReq.getUsername());
    }

    @Override
    public Optional<User> findUserByEmailAddress(String emailAddress) {
        return userRepository.findByContactDetailEmailAddress(emailAddress);
    }
}

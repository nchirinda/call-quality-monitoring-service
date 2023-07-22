package com.geneinsure.cqmservice.controller;

import com.geneinsure.cqmservice.controller.payload.ApiResp;
import com.geneinsure.cqmservice.controller.payload.LoginReq;
import com.geneinsure.cqmservice.model.entity.User;
import com.geneinsure.cqmservice.service.AbstractService;
import com.geneinsure.cqmservice.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@RestController
@RequestMapping(path = "users")
public class UsersController extends AbstractController<User> {

    private final UserServiceImpl userService;

    public UsersController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    protected AbstractService<User> getService() {
        return userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginReq loginReq) {

        Optional<User> loginUserOptional = userService.authenticateUser(loginReq);

        if (loginUserOptional.isPresent()) {
            return ResponseEntity.ok(loginUserOptional.get());
        } else return ResponseEntity.badRequest().body(new ApiResp("Invalid Credentials"));
    }
}

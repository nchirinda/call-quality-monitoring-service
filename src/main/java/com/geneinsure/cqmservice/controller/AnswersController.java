package com.geneinsure.cqmservice.controller;

import com.geneinsure.cqmservice.model.entity.Answer;
import com.geneinsure.cqmservice.service.AbstractService;
import com.geneinsure.cqmservice.service.AnswerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@RestController
@RequestMapping(path = "answers")
public class AnswersController extends AbstractController<Answer> {

    private final AnswerServiceImpl answerService;

    @Autowired
    public AnswersController(AnswerServiceImpl answerService) {
        this.answerService = answerService;
    }

    @Override
    protected AbstractService<Answer> getService() {
        return answerService;
    }
}

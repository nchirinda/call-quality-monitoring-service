package com.geneinsure.cqmservice.controller;

import com.geneinsure.cqmservice.model.entity.Question;
import com.geneinsure.cqmservice.service.AbstractService;
import com.geneinsure.cqmservice.service.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@RestController
@RequestMapping(path = "questions")
public class QuestionsController extends AbstractController<Question> {

    private final QuestionServiceImpl questionService;

    @Autowired
    public QuestionsController(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

    @Override
    protected AbstractService<Question> getService() {
        return questionService;
    }
}

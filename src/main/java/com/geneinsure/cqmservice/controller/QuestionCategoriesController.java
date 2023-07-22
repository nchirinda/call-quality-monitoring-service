package com.geneinsure.cqmservice.controller;

import com.geneinsure.cqmservice.model.entity.QuestionCategory;
import com.geneinsure.cqmservice.service.AbstractService;
import com.geneinsure.cqmservice.service.QuestionCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@RestController
@RequestMapping(path = "question_categories")
public class QuestionCategoriesController extends AbstractController<QuestionCategory> {

    private final QuestionCategoryServiceImpl questionCategoryService;

    @Autowired
    public QuestionCategoriesController(QuestionCategoryServiceImpl questionCategoryService) {
        this.questionCategoryService = questionCategoryService;
    }

    @Override
    protected AbstractService<QuestionCategory> getService() {
        return questionCategoryService;
    }
}

package com.geneinsure.cqmservice.service;

import com.geneinsure.cqmservice.model.entity.QuestionCategory;

import java.util.Optional;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

public interface QuestionCategoryService {

    Optional<QuestionCategory> findQuestionCategoryByName(String categoryName);
}

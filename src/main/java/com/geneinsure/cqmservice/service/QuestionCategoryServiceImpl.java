package com.geneinsure.cqmservice.service;

import com.geneinsure.cqmservice.model.entity.QuestionCategory;
import com.geneinsure.cqmservice.repository.QuestionCategoryRepository;
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
public class QuestionCategoryServiceImpl extends AbstractService<QuestionCategory> implements QuestionCategoryService {

    private final QuestionCategoryRepository questionCategoryRepository;

    @Autowired
    public QuestionCategoryServiceImpl(QuestionCategoryRepository questionCategoryRepository) {
        this.questionCategoryRepository = questionCategoryRepository;
    }

    @Override
    protected JpaRepository<QuestionCategory, String> getRepository() {
        return questionCategoryRepository;
    }

    @Override
    public Optional<QuestionCategory> findQuestionCategoryByName(String categoryName) {
        return questionCategoryRepository.findByName(categoryName);
    }
}

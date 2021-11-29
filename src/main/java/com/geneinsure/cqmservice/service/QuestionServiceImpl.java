package com.geneinsure.cqmservice.service;

import com.geneinsure.cqmservice.model.entity.Question;
import com.geneinsure.cqmservice.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@Transactional
@Service
public class QuestionServiceImpl extends AbstractService<Question> implements QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    protected JpaRepository<Question, String> getRepository() {
        return questionRepository;
    }

}

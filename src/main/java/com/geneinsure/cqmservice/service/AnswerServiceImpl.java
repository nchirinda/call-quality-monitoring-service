package com.geneinsure.cqmservice.service;

import com.geneinsure.cqmservice.model.entity.Answer;
import com.geneinsure.cqmservice.repository.AnswerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@Transactional
@Service
public class AnswerServiceImpl extends AbstractService<Answer> implements AnswerService {

    private static final Logger log = LoggerFactory.getLogger(AnswerServiceImpl.class);

    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    protected JpaRepository<Answer, String> getRepository() {
        return answerRepository;
    }
}

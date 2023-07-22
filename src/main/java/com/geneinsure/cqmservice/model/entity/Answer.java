package com.geneinsure.cqmservice.model.entity;

import com.geneinsure.cqmservice.model.enums.AnswerType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * @author Chirinda Nyasha 26/11/2021
 */

@Entity
@Table(name = "answers")
public class Answer extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    private AnswerType answerType;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private int score;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private int maxPossible;

    @OneToOne
    @NotNull
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    public AnswerType getAnswerType() {
        return answerType;
    }

    public void setAnswerType(AnswerType answerType) {
        this.answerType = answerType;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMaxPossible() {
        return maxPossible;
    }

    public void setMaxPossible(int maxPossible) {
        this.maxPossible = maxPossible;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}

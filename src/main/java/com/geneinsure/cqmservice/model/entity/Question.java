package com.geneinsure.cqmservice.model.entity;

import com.geneinsure.cqmservice.model.enums.AnswerType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author Chirinda Nyasha 26/11/2021
 */

@Entity
@Table(name = "questions")
public class Question extends AbstractEntity {

    @NotNull
    @Positive
    @Column(nullable = false)
    private Long number;

    @NotBlank
    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "name", nullable = false)
    private QuestionCategory questionCategory;

    @Enumerated(EnumType.STRING)
    private AnswerType answerType;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuestionCategory getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(QuestionCategory questionCategory) {
        this.questionCategory = questionCategory;
    }

    public AnswerType getAnswerType() {
        return answerType;
    }

    public void setAnswerType(AnswerType answerType) {
        this.answerType = answerType;
    }
}

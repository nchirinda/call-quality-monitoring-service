package com.geneinsure.cqmservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

/**
 * @author Chirinda Nyasha 26/11/2021
 */

@Entity
@Table(name = "question_categories", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class QuestionCategory extends AbstractEntity {

    @NotBlank
    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

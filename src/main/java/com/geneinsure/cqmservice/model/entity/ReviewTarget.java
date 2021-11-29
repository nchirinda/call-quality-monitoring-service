package com.geneinsure.cqmservice.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;

/**
 * @author Chirinda Nyasha 26/11/2021
 */

@Entity
@Table(name = "review_target")
public class ReviewTarget extends AbstractEntity {

    @NotNull
    @PositiveOrZero
    @Max(100)
    @Column(nullable = false)
    private int minPercentage;

    @NotNull
    @Positive
    @Max(100)
    @Column(nullable = false)
    private int maxPercentage;

    @NotBlank
    @Column(nullable = false)
    private String message;

    public int getMinPercentage() {
        return minPercentage;
    }

    public void setMinPercentage(int minPercentage) {
        this.minPercentage = minPercentage;
    }

    public int getMaxPercentage() {
        return maxPercentage;
    }

    public void setMaxPercentage(int maxPercentage) {
        this.maxPercentage = maxPercentage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

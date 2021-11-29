package com.geneinsure.cqmservice.model.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.*;

/**
 * @author Chirinda Nyasha 26/11/2021
 */

@Entity
@Table(name = "reviews")
public class Review extends AbstractEntity {

    @NotNull
    @Column(nullable = false)
    private LocalDate reviewDate;

    @NotBlank
    @Column(nullable = false)
    private String callReference;

    @NotBlank
    @Column(nullable = false)
    private String callRecordingReference;

    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {PERSIST, MERGE, REMOVE})
    @JoinTable(name = "review_answers",
            joinColumns = @JoinColumn(name = "review_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Set<Answer> answers = new HashSet<>();

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Long totalScore;

    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Long maxPossibleScore;

    @NotNull
    @PositiveOrZero
    @Max(100)
    @Column(nullable = false)
    private Double percentageScore;

    @OneToOne
    @NotNull
    @JoinColumn(name = "review_target_id", nullable = false)
    private ReviewTarget reviewTarget;

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getCallReference() {
        return callReference;
    }

    public void setCallReference(String callReference) {
        this.callReference = callReference;
    }

    public String getCallRecordingReference() {
        return callRecordingReference;
    }

    public void setCallRecordingReference(String callRecordingReference) {
        this.callRecordingReference = callRecordingReference;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public Long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
    }

    public Long getMaxPossibleScore() {
        return maxPossibleScore;
    }

    public void setMaxPossibleScore(Long maxPossibleScore) {
        this.maxPossibleScore = maxPossibleScore;
    }

    public Double getPercentageScore() {
        return percentageScore;
    }

    public void setPercentageScore(Double percentageScore) {
        this.percentageScore = percentageScore;
    }

    public ReviewTarget getReviewTarget() {
        return reviewTarget;
    }

    public void setReviewTarget(ReviewTarget reviewTarget) {
        this.reviewTarget = reviewTarget;
    }
}

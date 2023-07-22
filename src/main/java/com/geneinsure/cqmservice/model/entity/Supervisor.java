package com.geneinsure.cqmservice.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.CascadeType.*;

/**
 * @author Chirinda Nyasha 26/11/2021
 */

@Entity
@Table(name = "supervisor")
public class Supervisor extends AbstractEntity {

    @Column(nullable = false)
    private long number;

    @OneToOne(cascade = {PERSIST, MERGE, REMOVE})
    @NotNull
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package com.geneinsure.cqmservice.model.entity;

import com.geneinsure.cqmservice.model.enums.RoleType;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author Chirinda Nyasha 26/11/2021
 */

@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleType name;

    @NotBlank
    @Column(nullable = false)
    private String description;

    public RoleType getName() {
        return name;
    }

    public void setName(RoleType roleType) {
        this.name = roleType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

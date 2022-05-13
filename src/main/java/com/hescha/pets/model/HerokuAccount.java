package com.hescha.pets.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class HerokuAccount extends AbstractModel {

    private String herokuUsername;

    private String githubUsername;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Project.class, cascade = CascadeType.DETACH)
    private Set<Project> projects = new HashSet<>();
}

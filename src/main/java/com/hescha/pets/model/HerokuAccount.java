package com.hescha.pets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HerokuAccount extends AbstractModel {

    private String herokuUsername;

    private String githubUsername;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Project.class, cascade = CascadeType.DETACH)
    private Set<Project> projects = new HashSet<>();
}

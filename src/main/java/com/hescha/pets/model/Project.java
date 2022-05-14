package com.hescha.pets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Project extends AbstractModel {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 1000)
    private String description;

    private String url;

}

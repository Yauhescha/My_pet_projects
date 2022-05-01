package com.hescha.pets.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class Project extends AbstractModel{

    @Column(nullable=false, unique=true)
    private String name;

    private String description;

    private String url;
}

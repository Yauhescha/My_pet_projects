package com.hescha.pets.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class AbstractModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
}

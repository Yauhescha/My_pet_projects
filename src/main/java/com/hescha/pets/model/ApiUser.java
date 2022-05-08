package com.hescha.pets.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "my_user")
public class ApiUser extends AbstractModel {
    private String username;
    private String password;
}

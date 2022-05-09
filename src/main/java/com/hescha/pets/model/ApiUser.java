package com.hescha.pets.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "my_user")
public class ApiUser extends AbstractModel {

    private String username;

    @JsonIgnore
    private String password;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @Override
    public String toString() {
        return "ApiUser{" +
                "username='" + username + '\'' +
                ", roles=" + Arrays.toString(roles.toArray())+
                '}';
    }
}

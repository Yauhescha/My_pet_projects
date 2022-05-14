package com.hescha.pets.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "my_user")
@EqualsAndHashCode(callSuper = true)
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

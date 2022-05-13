package com.hescha.pets.service;

import com.hescha.pets.model.ApiUser;
import com.hescha.pets.requestmodel.UserCreateRequest;

public interface UserService extends CrudService<ApiUser> {
    ApiUser readUserByUsername(String username);

    ApiUser create(UserCreateRequest userCreateRequest);
}

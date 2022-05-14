package com.hescha.pets.service.impl;

import com.hescha.pets.exception.UserException;
import com.hescha.pets.model.ApiUser;
import com.hescha.pets.repository.UserRepository;
import com.hescha.pets.requestmodel.UserCreateRequest;
import com.hescha.pets.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserServiceImpl extends CrudServiceImpl<ApiUser> implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ApiUser readUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }


    public ApiUser create(UserCreateRequest userCreateRequest) {
        Optional<ApiUser> byUsername = userRepository.findByUsername(userCreateRequest.getUsername());
        if (byUsername.isPresent()) {
            throw new UserException.UserWithSameUsernameAlreadyExistsException(userCreateRequest.getUsername());
        }

        ApiUser user = new ApiUser();
        user.setUsername(userCreateRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
        return super.create(user);
    }
}

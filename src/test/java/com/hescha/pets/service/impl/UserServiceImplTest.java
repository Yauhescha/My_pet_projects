package com.hescha.pets.service.impl;

import com.hescha.pets.exception.UserException;
import com.hescha.pets.model.ApiUser;
import com.hescha.pets.repository.UserRepository;
import com.hescha.pets.requestmodel.UserCreateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    private ApiUser user;
    private UserCreateRequest userCreateRequest;

    @BeforeEach
    void setUp() {
        user = new ApiUser(USERNAME, PASSWORD, null);
        userCreateRequest = new UserCreateRequest(USERNAME, PASSWORD);
    }

    @Test
    void readUserByUsername() {
        // Given
        when(repository.findByUsername(anyString())).thenReturn(ofNullable(user));

        // When
        ApiUser actual = service.readUserByUsername(anyString());

        // Then
        Assertions.assertEquals(actual, user);
    }

    @Test
    void readUserByUsername_notExists() {
        // Given
        when(repository.findByUsername(anyString())).thenReturn(empty());

        // When
        Executable executable = () -> service.readUserByUsername(anyString());

        // Then
        Assertions.assertThrows(EntityNotFoundException.class, executable);
    }

    @Test
    void create_usernameAlreadyExist() {
        // Given
        when(repository.findByUsername(anyString())).thenReturn(ofNullable(user));

        // When
        Executable executable = () -> service.create(userCreateRequest);

        Exception exception =
            Assertions.assertThrows(UserException.UserWithSameUsernameAlreadyExistsException.class, executable);

        // Then
        assertTrue(exception.getMessage().startsWith("User with username"));
        assertTrue(exception.getMessage().endsWith("already registered. Please use different username."));
    }
}
package com.hescha.pets.controller;

import com.hescha.pets.model.ApiUser;
import com.hescha.pets.requestmodel.UserCreateRequest;
import com.hescha.pets.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    @ApiOperation("Method to get all users")
    public ResponseEntity<List<ApiUser>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    @ApiOperation("Method to create a new user")
    public ResponseEntity<ApiUser> createUser(@RequestBody UserCreateRequest createRequest) {
        return ResponseEntity.ok(userService.create(createRequest));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Method to delete user by id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}

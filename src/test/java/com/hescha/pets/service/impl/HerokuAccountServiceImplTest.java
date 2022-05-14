package com.hescha.pets.service.impl;

import com.hescha.pets.exception.HerokuAccountException;
import com.hescha.pets.model.HerokuAccount;
import com.hescha.pets.model.Project;
import com.hescha.pets.repository.HerokuAccountRepository;
import com.hescha.pets.service.ProjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class HerokuAccountServiceImplTest {
    private static final String USERNAME_GIT = "Username_Git";
    private static final String USERNAME_HEROKU = "Username_Heroku";

    @Mock
    private ProjectService projectService;

    @Mock
    private HerokuAccountRepository repository;

    @InjectMocks
    private HerokuAccountServiceImpl service;

    private HerokuAccount account;

    private Project project;

    @BeforeEach
    void setUp() {
        account = new HerokuAccount(USERNAME_HEROKU, USERNAME_GIT, new HashSet<>());
        project = new Project(USERNAME_GIT, null, null);
    }

    @Test
    void update() {
        // Given
        HerokuAccount newInfo = new HerokuAccount("newHeroku", "newGit", null);
        when(repository.findById(anyLong())).thenReturn(Optional.of(account));

        // When
        service.update(1L, newInfo);

        // Then
        assertEquals(newInfo.getGithubUsername(), account.getGithubUsername());
        assertEquals(newInfo.getHerokuUsername(), account.getHerokuUsername());
    }

    @Test
    void addProjectToAccount() {
        // Given
        when(repository.findById(anyLong())).thenReturn(Optional.of(account));
        when(projectService.read(anyLong())).thenReturn(project);

        // When
        service.addProjectToAccount(1L, 1L);

        // Then
        assertEquals(account.getProjects().size(), 1);
        assertTrue(account.getProjects().contains(project));
    }

    @Test
    void addProjectToAccount_projectAlreadyAdded() {
        // Given
        account.getProjects().add(project);
        when(repository.findById(anyLong())).thenReturn(Optional.of(account));
        when(projectService.read(anyLong())).thenReturn(project);

        // When
        Executable executable = () -> service.addProjectToAccount(1L, 1L); //prepare Executable with invocation of the method on your system under test

        Exception exception = Assertions.assertThrows(HerokuAccountException.HerokuAccountAlreadyContainsThisProjectException.class, executable); // you can even assign it to MyCustomException type variable

        // Then
        assertTrue(exception.getMessage().startsWith("Heroku Account already contains Project "));
    }

    @Test
    void removeProjectFromAccount_projectNotInAccount() {
        // Given
        when(repository.findById(anyLong())).thenReturn(Optional.of(account));
        when(projectService.read(anyLong())).thenReturn(project);

        // When
        Executable executable = () -> service.removeProjectFromAccount(1L, 1L); //prepare Executable with invocation of the method on your system under test

        Exception exception = Assertions.assertThrows(HerokuAccountException.HerokuAccountDoesntHaveThisProject.class, executable); // you can even assign it to MyCustomException type variable

        // Then
        assertTrue(exception.getMessage().startsWith("You cannot delete project "));
        assertTrue(exception.getMessage().endsWith(", because Heroku Account does not contains it."));
    }
}
package com.hescha.pets.service.impl;

import com.hescha.pets.dto.ProjectDTO;
import com.hescha.pets.exception.ProjectException;
import com.hescha.pets.model.Project;
import com.hescha.pets.repository.ProjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProjectServiceImplTest {
    private static final String NAME = "Project";
    private static final String DESCRIPTION = "About project";
    private static final String URL = "https://...";

    private static final String NAME_NEW = NAME + "new";
    private static final String DESCRIPTION_NEW = DESCRIPTION + "new";
    private static final String URL_NEW = URL + "new";

    @InjectMocks
    private ProjectServiceImpl service;

    @Mock
    private ProjectRepository repository;

    private Project project;

    private ProjectDTO projectDTO;

    @BeforeEach
    void setUp() {
        project = new Project(NAME, DESCRIPTION, URL);
        projectDTO = ProjectDTO.builder()
            .name(NAME_NEW)
            .description(DESCRIPTION_NEW)
            .url(URL_NEW)
            .build();
    }

    @Test
    void create_nameAlreadyExists() {
        //Given
        projectDTO.setName(NAME);
        when(repository.existsByName(anyString())).thenReturn(true);

        // When
        Executable executable = () -> service.create(projectDTO);

        Exception exception =
            Assertions.assertThrows(ProjectException.ProjectWithSameNameAlreadyExists.class, executable);

        // Then
        assertTrue(exception.getMessage().startsWith("Project with name "));
        assertTrue(exception.getMessage().endsWith(" already exists. Choose another one."));
    }

    @Test
    void update() {
        // Given
        when(repository.findById(anyLong())).thenReturn(ofNullable(project));

        // When
        service.update(1L, projectDTO);

        // Then
        assertEquals(NAME_NEW, project.getName());
        assertEquals(DESCRIPTION_NEW, project.getDescription());
        assertEquals(URL_NEW, project.getUrl());
    }

    @Test
    void update_newNameAlreadyExists() {
        // Given
        when(repository.findById(anyLong())).thenReturn(ofNullable(project));
        when(repository.existsByName(anyString())).thenReturn(true);

        // When
        Executable executable = () -> service.update(1L, projectDTO);

        Exception exception =
            Assertions.assertThrows(ProjectException.ProjectWithSameNameAlreadyExists.class, executable);

        // Then
        assertTrue(exception.getMessage().startsWith("Project with name "));
        assertTrue(exception.getMessage().endsWith(" already exists. Choose another one."));
    }
}
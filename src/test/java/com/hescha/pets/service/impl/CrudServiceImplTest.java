package com.hescha.pets.service.impl;

import com.hescha.pets.model.Project;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.util.Optional.of;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CrudServiceImplTest {

    @Mock
    private JpaRepository<Project, Long> repository;

    @InjectMocks
    private CrudServiceImpl<Project> crudService;

    @Test
    void findAll() {
        // When
        crudService.findAll();

        // Then
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void find() {
        // When
        crudService.find(anyLong());

        // Then
        verify(repository).findById(anyLong());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void read() {
        //Given
        when(repository.findById(anyLong())).thenReturn(of(new Project()));

        // When
        crudService.read(anyLong());

        // Then
        verify(repository).findById(anyLong());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void create() {
        // When
        crudService.create(any());

        // Then
        verify(repository).saveAndFlush(any());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void delete() {
        // When
        crudService.delete(anyLong());

        // Then
        verify(repository).deleteById(anyLong());
        verifyNoMoreInteractions(repository);
    }
}
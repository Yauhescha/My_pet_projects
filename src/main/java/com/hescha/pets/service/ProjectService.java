package com.hescha.pets.service;

import com.hescha.pets.dto.ProjectDTO;
import com.hescha.pets.model.Project;
import com.hescha.pets.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    public Project create(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setUrl(projectDTO.getUrl());
        return projectRepository.save(project);
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    public Project update(ProjectDTO projectDTO) {
        Project project;
        if (projectDTO.getId() == null) {
            project = new Project();
        } else {
            project = projectRepository.findById(projectDTO.getId()).orElseThrow(EntityNotFoundException::new);
        }

        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setUrl(projectDTO.getUrl());
        return projectRepository.save(project);
    }
}

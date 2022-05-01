package com.hescha.pets.service;

import com.hescha.pets.dto.ProjectDTO;
import com.hescha.pets.exception.ModelNotFoundException;
import com.hescha.pets.model.Project;
import com.hescha.pets.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    public Project update(Long id, ProjectDTO projectDTO) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();

            project.setName(projectDTO.getName());
            project.setDescription(projectDTO.getDescription());
            project.setUrl(projectDTO.getUrl());
            return projectRepository.save(project);
        } else {
            throw new ModelNotFoundException(id);
        }
    }
}

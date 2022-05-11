package com.hescha.pets.service;

import com.hescha.pets.dto.ProjectDTO;
import com.hescha.pets.exception.ProjectException;
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

    public Project findById(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            return optionalProject.get();
        } else
            throw new EntityNotFoundException();
    }

    public Project create(ProjectDTO projectDTO) {
        if (projectRepository.existsByName(projectDTO.getName())) {
            throw new ProjectException.ProjectWithSameNameAlreadyExists();
        }

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
        Project project = projectRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setUrl(projectDTO.getUrl());
        return projectRepository.save(project);
    }
}

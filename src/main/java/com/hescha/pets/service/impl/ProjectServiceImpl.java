package com.hescha.pets.service.impl;

import com.hescha.pets.dto.ProjectDTO;
import com.hescha.pets.exception.ProjectException;
import com.hescha.pets.model.Project;
import com.hescha.pets.repository.ProjectRepository;
import com.hescha.pets.service.ProjectService;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl extends CrudServiceImpl<Project> implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository repository) {
        super(repository);
        this.projectRepository = repository;
    }

    public Project create(ProjectDTO projectDTO) {
        if (projectRepository.existsByName(projectDTO.getName())) {
            throw new ProjectException.ProjectWithSameNameAlreadyExists();
        }

        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setUrl(projectDTO.getUrl());
        return super.create(project);
    }

    public Project update(Long id, ProjectDTO projectDTO) {
        Project project = read(id);

        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setUrl(projectDTO.getUrl());
        return super.update(project);
    }
}

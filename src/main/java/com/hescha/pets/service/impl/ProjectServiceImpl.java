package com.hescha.pets.service.impl;

import com.hescha.pets.dto.ProjectDTO;
import com.hescha.pets.dto.transformer.ProjectTransformer;
import com.hescha.pets.exception.ProjectException;
import com.hescha.pets.model.HerokuAccount;
import com.hescha.pets.model.Project;
import com.hescha.pets.repository.ProjectRepository;
import com.hescha.pets.service.HerokuAccountService;
import com.hescha.pets.service.ProjectService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Setter
public class ProjectServiceImpl extends CrudServiceImpl<Project> implements ProjectService {

    private ProjectRepository projectRepository;

    @Autowired
    private HerokuAccountService herokuAccountService;

    public ProjectServiceImpl(ProjectRepository repository) {
        super(repository);
        this.projectRepository = repository;
    }

    public Project create(ProjectDTO projectDTO) {
        if (projectRepository.existsByName(projectDTO.getName())) {
            throw new ProjectException.ProjectWithSameNameAlreadyExists(projectDTO.getName());
        }

        Project project = ProjectTransformer.toModel(projectDTO);
        return super.create(project);
    }

    public Project update(Long id, ProjectDTO projectDTO) {
        Project project = read(id);
        if (!project.getName().equals(projectDTO.getName()) && projectRepository.existsByName(projectDTO.getName())) {
            throw new ProjectException.ProjectWithSameNameAlreadyExists(projectDTO.getName());
        }

        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setUrl(projectDTO.getUrl());
        return super.update(project);
    }

    @Override
    public void delete(Long id) {
        Project project = read(id);
        HerokuAccount account = herokuAccountService.findByProject(project);
        if (account != null) {
            herokuAccountService.removeProjectFromAccount(account.getId(), id);
        }
        super.delete(id);
    }
}

package com.hescha.pets.service.impl;

import com.hescha.pets.exception.HerokuAccountException.HerokuAccountAlreadyContainsThisProjectException;
import com.hescha.pets.exception.HerokuAccountException.HerokuAccountDoesntHaveThisProject;
import com.hescha.pets.model.HerokuAccount;
import com.hescha.pets.model.Project;
import com.hescha.pets.repository.HerokuAccountRepository;
import com.hescha.pets.service.HerokuAccountService;
import com.hescha.pets.service.ProjectService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Setter
public class HerokuAccountServiceImpl extends CrudServiceImpl<HerokuAccount> implements HerokuAccountService {

    private HerokuAccountRepository repository;
    @Autowired
    private ProjectService projectService;

    public HerokuAccountServiceImpl(HerokuAccountRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public HerokuAccount update(Long id, HerokuAccount entity) {
        HerokuAccount read = read(id);
        read.setGithubUsername(entity.getGithubUsername());
        read.setHerokuUsername(entity.getHerokuUsername());
        return update(read);
    }

    @Override
    public void addProjectToAccount(Long accountId, Long projectId) {
        HerokuAccount entity = read(accountId);
        Project project = projectService.read(projectId);

        Set<Project> projects = entity.getProjects();
        if (projects.contains(project)) {
            throw new HerokuAccountAlreadyContainsThisProjectException(project.getName());
        }
        projects.add(project);
        update(entity);
    }

    @Override
    public void removeProjectFromAccount(Long accountId, Long projectId) {
        HerokuAccount entity = read(accountId);
        Project project = projectService.read(projectId);

        Set<Project> projects = entity.getProjects();
        if (!projects.contains(project)) {
            throw new HerokuAccountDoesntHaveThisProject(project.getName());
        }
        projects.remove(project);
        project.setHerokuAccount(null);
        update(entity);
        projectService.update(project);
    }

    @Override
    public HerokuAccount findByProject(Project project) {
        return repository.findHerokuAccountByProjectsContaining(project);
    }
}

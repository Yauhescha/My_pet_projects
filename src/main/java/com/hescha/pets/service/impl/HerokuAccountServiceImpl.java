package com.hescha.pets.service.impl;

import com.hescha.pets.exception.HerokuAccountException.HerokuAccountAlreadyContainsThisProjectException;
import com.hescha.pets.exception.HerokuAccountException.HerokuAccountDoesntHaveThisProject;
import com.hescha.pets.model.HerokuAccount;
import com.hescha.pets.model.Project;
import com.hescha.pets.repository.HerokuAccountRepository;
import com.hescha.pets.service.HerokuAccountService;
import com.hescha.pets.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class HerokuAccountServiceImpl extends CrudServiceImpl<HerokuAccount> implements HerokuAccountService {

    private final ProjectService projectService;

    public HerokuAccountServiceImpl(HerokuAccountRepository repository,
                                    ProjectService projectService) {
        super(repository);
        this.projectService = projectService;
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
            throw new HerokuAccountAlreadyContainsThisProjectException();
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
            throw new HerokuAccountDoesntHaveThisProject();
        }
        projects.remove(project);
        update(entity);
    }
}

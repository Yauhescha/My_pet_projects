package com.hescha.pets.service;

import com.hescha.pets.model.HerokuAccount;
import com.hescha.pets.model.Project;

public interface HerokuAccountService extends CrudService<HerokuAccount> {
    HerokuAccount update(Long id, HerokuAccount entity);

    HerokuAccount findByProject(Project project);

    void addProjectToAccount(Long accountId, Long projectId);

    void removeProjectFromAccount(Long accountId, Long id);
}

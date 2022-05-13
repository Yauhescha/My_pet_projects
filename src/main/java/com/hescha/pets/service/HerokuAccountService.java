package com.hescha.pets.service;

import com.hescha.pets.model.HerokuAccount;

public interface HerokuAccountService extends CrudService<HerokuAccount> {
    HerokuAccount update(Long id, HerokuAccount entity);

    void addProjectToAccount(Long accountId, Long projectId);

    void removeProjectFromAccount(Long accountId, Long id);
}

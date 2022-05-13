package com.hescha.pets.service;

import com.hescha.pets.dto.ProjectDTO;
import com.hescha.pets.model.Project;

public interface ProjectService extends CrudService<Project> {

    Project update(Long id, ProjectDTO entity);
}

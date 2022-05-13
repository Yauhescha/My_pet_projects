package com.hescha.pets.dto.transformer;

import com.hescha.pets.dto.ProjectDTO;
import com.hescha.pets.model.Project;

public class ProjectTransformer {
    public static ProjectDTO toDTO(Project project) {
        return ProjectDTO.builder()
            .id(project.getId())
            .name(project.getName())
            .description(project.getDescription())
            .url(project.getUrl())
            .build();
    }

    public static Project toModel(ProjectDTO dto) {
        Project project = new Project();
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setUrl(dto.getUrl());
        return project;
    }
}

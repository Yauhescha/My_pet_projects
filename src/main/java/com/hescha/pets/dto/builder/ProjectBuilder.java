package com.hescha.pets.dto.builder;

import com.hescha.pets.dto.ProjectDTO;
import com.hescha.pets.model.Project;

public class ProjectBuilder {
    public static ProjectDTO toDTO(Project project) {
        return ProjectDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .url(project.getUrl())
                .build();
    }
}

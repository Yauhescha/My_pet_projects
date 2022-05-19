package com.hescha.pets.controller;

import com.hescha.pets.dto.ProjectDTO;
import com.hescha.pets.dto.transformer.ProjectTransformer;
import com.hescha.pets.model.Project;
import com.hescha.pets.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    @ApiOperation("Method to create a new project")
    public ResponseEntity<ProjectDTO> create(@RequestBody ProjectDTO projectDTO) {
        Project project = projectService.create(ProjectTransformer.toModel(projectDTO));
        return ResponseEntity.ok(ProjectTransformer.toDTO(project));
    }

    @GetMapping
    @ApiOperation("Method to get all project")
    public ResponseEntity<List<ProjectDTO>> readAll() {
        List<ProjectDTO> projects = projectService.findAll().stream()
                .map(ProjectTransformer::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    @ApiOperation("Read project by id")
    public ResponseEntity<ProjectDTO> read(@PathVariable("id") Long id) {
        Project project = projectService.read(id);
        return ResponseEntity.ok(ProjectTransformer.toDTO(project));
    }

    @PutMapping("/{id}")
    @ApiOperation("Update project by id")
    public ResponseEntity<ProjectDTO> update(@PathVariable("id") Long id,
                                             @RequestBody ProjectDTO projectDTO) {
        Project project = projectService.update(id, projectDTO);
        return ResponseEntity.ok(ProjectTransformer.toDTO(project));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete project by id")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        projectService.delete(id);
        return ResponseEntity.ok().build();
    }

}

package com.hescha.pets.controller;

import com.hescha.pets.dto.ProjectDTO;
import com.hescha.pets.model.Project;
import com.hescha.pets.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    private final String mainPage = "view/project";

    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("entities", projectService.getAll());
        return mainPage;
    }

    @PostMapping()
    public String creat(@ModelAttribute ProjectDTO projectDTO) {
        Project project = projectService.create(projectDTO);
        return "redirect:/project/" + project.getId();
    }

    @GetMapping("/{id}")
    public String read(Model model, @PathVariable("id") Long id) {
        model.addAttribute("entity", projectService.findById(id).get());
        return mainPage;
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute ProjectDTO projectDTO) {
        projectService.update(id, projectDTO);
        return "redirect:/project/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        projectService.delete(id);
        return "redirect:/project";
    }

}

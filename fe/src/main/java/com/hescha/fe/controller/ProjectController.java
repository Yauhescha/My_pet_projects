package com.hescha.fe.controller;

import com.hescha.fe.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Value("${be.api.adres}")
    private String apiAdres;
    private final RestTemplate template = new RestTemplate();

    @GetMapping
    public String getPage(Model model) {
        ResponseEntity<List> response = template.getForEntity(apiAdres + "/project", List.class);
        model.addAttribute("list", response.getBody());
        return "project";
    }

    @PostMapping
    public String createORUpdate(@ModelAttribute ProjectDTO projectDTO) {
        if (projectDTO.getId() == null) {
            template.postForObject(apiAdres + "/project", projectDTO, ProjectDTO.class);
        } else {
            template.put(apiAdres + "/project/" + projectDTO.getId(), projectDTO, ProjectDTO.class);
        }
        return "redirect:/project";
    }

    @GetMapping(path = {"/edit/{id}", "/edit"})
    public String editProject(Model model, @PathVariable(name = "id", required = false) Long id) {
        ProjectDTO dto;
        if (id != null) {
            ResponseEntity<ProjectDTO> response = template.getForEntity(apiAdres + "/project" + "/" + id, ProjectDTO.class);
            dto = response.getBody();
        } else {
            dto = new ProjectDTO();
        }
        model.addAttribute("entity", dto);
        return "project-edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable("id") Long id) {
        template.delete(apiAdres + "/project" + "/" + id);
        return "redirect:/project";
    }
}

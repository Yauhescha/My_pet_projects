package com.hescha.fe.controller;

import com.hescha.fe.dto.ProjectDTO;
import org.springframework.beans.factory.annotation.Value;
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
    private final String path = apiAdres + "/project";

    @GetMapping
    public String getPage(Model model) {
        ResponseEntity<List> response = template.getForEntity(path, List.class);
        model.addAttribute("list", response.getBody());
        return "project";
    }

    @PostMapping
    public String createORUpdate(@ModelAttribute ProjectDTO projectDTO) {
        if (projectDTO.getId() == null) {
            template.postForObject(path, projectDTO, ProjectDTO.class);
        } else {
            template.put(path + "/" + projectDTO.getId(), projectDTO, ProjectDTO.class);
        }
        return "redirect:/project";
    }

    @GetMapping(path = {"/edit/{id}", "/edit"})
    public String editProject(Model model, @PathVariable(name = "id", required = false) Long id) {
        ProjectDTO dto;
        if (id != null) {
            ResponseEntity<ProjectDTO> response = template.getForEntity(path + "/" + id, ProjectDTO.class);
            dto = response.getBody();
        } else {
            dto = new ProjectDTO();
        }
        model.addAttribute("entity", dto);
        return "project-edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable("id") Long id) {
        template.delete(path + "/" + id);
        return "redirect:/project";
    }
}

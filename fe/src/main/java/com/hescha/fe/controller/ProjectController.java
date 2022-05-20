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
    private RestTemplate template = new RestTemplate();

    @GetMapping
    public String getPage(Model model) {
        ResponseEntity<List> response = template.getForEntity(apiAdres + "/project", List.class);
        List body = response.getBody();
        model.addAttribute("list", body);
        return "project";
    }

    @PostMapping
    public String create(@RequestBody ProjectDTO projectDTO) {
        template.postForEntity(apiAdres + "/project", projectDTO, ResponseEntity.class);
        return "redirect:/project";
    }

    @GetMapping("/edit/{id}")
    public String editProject(Model model, @PathVariable("id") Long id) {
        ResponseEntity<ProjectDTO> response = template.getForEntity(apiAdres + "/project" + "/" + id, ProjectDTO.class);
        model.addAttribute("entity", response.getBody());
        return "project-edit";
    }
}

package com.hescha.pets.controller;

import com.hescha.pets.model.HerokuAccount;
import com.hescha.pets.service.HerokuAccountService;
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

@Controller
@RequestMapping("/heroku")
@RequiredArgsConstructor
public class HerokuAccountController {

    private final HerokuAccountService herokuAccountService;

    @PostMapping
    @ApiOperation("Create heroku account")
    public ResponseEntity<HerokuAccount> create(@RequestBody HerokuAccount herokuAccount) {
        HerokuAccount entity = herokuAccountService.create(herokuAccount);
        return ResponseEntity.ok(entity);
    }

    @GetMapping
    @ApiOperation("Read all heroku accounts")
    public ResponseEntity<List<HerokuAccount>> readAll() {
        List<HerokuAccount> projects = herokuAccountService.findAll();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    @ApiOperation("Read heroku account by id")
    public ResponseEntity<HerokuAccount> read(@PathVariable("id") Long id) {
        HerokuAccount entity = herokuAccountService.read(id);
        return ResponseEntity.ok(entity);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update heroku account by id")
    public ResponseEntity<HerokuAccount> update(@PathVariable("id") Long id,
                                                @RequestBody HerokuAccount HerokuAccount) {
        HerokuAccount entity = herokuAccountService.update(id, HerokuAccount);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete heroku account by id")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        herokuAccountService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/project/{projectId}")
    @ApiOperation("Add selected project to Heroku account")
    public ResponseEntity<Void> addProjectToAccount(@PathVariable("id") Long id,
                                                    @PathVariable("projectId") Long projectId) {
        herokuAccountService.addProjectToAccount(id, projectId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/project/{projectId}")
    @ApiOperation("Remove selected project from heroku account")
    public ResponseEntity<Void> removeProjectFromAccount(@PathVariable("id") Long id,
                                                         @PathVariable("projectId") Long projectId) {
        herokuAccountService.removeProjectFromAccount(id, projectId);
        return ResponseEntity.ok().build();
    }

}

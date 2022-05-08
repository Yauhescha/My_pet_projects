package com.hescha.pets.repository;

import com.hescha.pets.model.Project;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}

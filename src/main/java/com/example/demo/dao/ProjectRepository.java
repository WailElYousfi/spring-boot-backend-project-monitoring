package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Project;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
//	Optional<Project> findByProjectId(Integer projectId);
	Optional<Project> findByProjectName(String projectName);

	Boolean existsByProjectId(Integer projectId);

	@Query("select count(p) from Project p where p.isClosed = ?1")
	Integer countProjects(boolean isClosed);
}
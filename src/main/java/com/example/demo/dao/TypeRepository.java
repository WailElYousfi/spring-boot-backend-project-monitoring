package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Type;



@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {
//	Optional<Project> findByProjectId(Integer projectId);
	Optional<Type> findByTypeName(String typeName);
}
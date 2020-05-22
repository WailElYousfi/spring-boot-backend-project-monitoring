package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.ERole;
import com.example.demo.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserId(Long userId);
	Optional<User> findByUsername(String username);
	Optional<User> findByJiraUsername(String jiraUsername);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	Boolean existsByJiraUsername(String jiraUsername);
	
	Boolean existsByUserCode(Long userCode);
	
	@Query("select count(u) from User u where u.isArchived = ?1")
	Integer countUsers(boolean isArchived);
	
	@Query("select count(u) from User u where u.isArchived = ?1 and u.role.name = ?2")
	Integer countUsersByRole(boolean isArchived, ERole name);
}
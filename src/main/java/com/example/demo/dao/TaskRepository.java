package com.example.demo.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	Optional<Task> findByKey(String key);

	Boolean existsByKey(String key);
	
	@Query("select t from Task t where t.key = ?1 and t.title = ?2")
	Optional<Task> findByKeyAndTitle(String key, String title);
	
	
	@Query("select t from Task t where t.date >= ?1 and t.date <= ?2")
	Optional< List<Task> > findByDate(Date startDate, Date endDate);
}
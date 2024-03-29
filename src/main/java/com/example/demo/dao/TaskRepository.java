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
	
	@Query("select t from Task t where t.key = ?1 and t.summary = ?2")
	Optional<Task> findByKeyAndTitle(String key, String title);
	
	
	@Query("select t from Task t where t.date >= ?1 and t.date <= ?2")
	Optional< List<Task> > findByDates(Date startDate, Date endDate);
	
	@Query("select t from Task t where t.assignedUser.userId = ?1 and t.status = ?2")
	Optional< List<Task> > findByUserIdAndStatus(Long userId, String status);
	
	@Query("select t from Task t where t.assignedUser.userId = ?1 and t.status = ?2 and EXTRACT(MONTH FROM t.resolved) = ?3")
	Optional< List<Task> > findByUserIdAndStatusAndDates(Long userId, String status, int month);
	
	@Query("select t from Task t where t.project.projectId = ?1 and t.status = ?2 and EXTRACT(MONTH FROM t.resolved) = ?3")
	Optional< List<Task> > findByProjectIdAndStatusAndDates(Integer projectId, String status, int month);
	
	@Query("select t from Task t where t.project.projectId = ?1 and t.status = ?2")
	Optional< List<Task> > findByProjectIdAndStatus(Integer projectId, String status);
	
	@Query("select t from Task t where t.date >= ?1 and t.date <= ?2 and t.fileType.typeId = ?3")
	Optional< List<Task> > findByDatesAndType(Date startDate, Date endDate, Integer type);
	
	@Query("select t from Task t where t.date >= ?1 and t.date <= ?2 and t.idOt = ?3")
	Optional< List<Task> > findByDatesAndIdOt(Date startDate, Date endDate, Integer idOt);

	@Query("select t from Task t where t.date >= ?1 and t.date <= ?2 and t.summary like %?3%")
	Optional<List<Task>> findByDatesAndSummary(Date startDate, Date endDate, String summary);
	
	@Query("select t from Task t where t.assignedUser.userId = ?1")
	Optional< List<Task> > findByUserId(Long userId);
}
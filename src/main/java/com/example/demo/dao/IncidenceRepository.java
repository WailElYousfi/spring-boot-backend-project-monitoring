package com.example.demo.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Incidence;
import com.example.demo.models.Task;


@Repository
public interface IncidenceRepository extends JpaRepository<Incidence, Integer> {
	Optional<Incidence> findByKey(String key);

	Boolean existsByKey(String key);
	
	@Query("select i from Incidence i where i.key = ?1 and i.summary = ?2")
	Optional<Incidence> findByKeyAndSummary(String key, String summary);
	
	
	@Query("select i from Incidence i where i.date >= ?1 and i.date <= ?2")
	Optional< List<Incidence> > findByDate(Date startDate, Date endDate);
	
	@Query("select i from Incidence i where i.causedUser.userId = ?1 and i.status = ?2 and EXTRACT(MONTH FROM i.resolved) = ?3 and i.fileType.typeName = ?4")
	Optional< List<Incidence> > findByUserIdAndStatusAndDatesAndType(Long userId, String status, int month, String typeName);
	
	@Query("select i from Incidence i where i.project.projectId = ?1 and i.status = ?2 and EXTRACT(MONTH FROM i.resolved) = ?3 and i.fileType.typeName = ?4")
	Optional< List<Incidence> > findByProjectIdAndStatusAndDatesAndType(Integer projectId, String status, int month, String typeName);
	
	@Query("select count(i) from Incidence i where i.causedUser.userId = ?1 and i.status = ?2 and EXTRACT(MONTH FROM i.resolved) = ?3  and i.fileType.typeName = ?4")
	Integer countIncidencesByUserIdAndStatusAndDatesAndType(Long userId, String status, int month, String typeName);
	
	@Query("select count(i) from Incidence i where i.project.projectId = ?1 and i.status = ?2 and EXTRACT(MONTH FROM i.resolved) = ?3  and i.fileType.typeName = ?4")
	Integer countIncidencesByProjectIdAndStatusAndDatesAndType(Integer projectId, String status, int month, String typeName);
}
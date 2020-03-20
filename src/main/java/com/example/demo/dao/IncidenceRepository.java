package com.example.demo.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Incidence;


@Repository
public interface IncidenceRepository extends JpaRepository<Incidence, Integer> {
	Optional<Incidence> findByKey(String key);

	Boolean existsByKey(String key);
	
	@Query("select i from Incidence i where i.key = ?1 and i.summary = ?2")
	Optional<Incidence> findByKeyAndSummary(String key, String summary);
	
	
	@Query("select i from Incidence i where i.date >= ?1 and i.date <= ?2")
	Optional< List<Incidence> > findByDate(Date startDate, Date endDate);
}
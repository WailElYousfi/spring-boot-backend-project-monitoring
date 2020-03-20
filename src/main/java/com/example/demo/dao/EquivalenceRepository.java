package com.example.demo.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Equivalence;


@Repository
public interface EquivalenceRepository extends JpaRepository<Equivalence, Integer> {
	Optional<Equivalence> findByColumnName(String columnName);
	Optional<Equivalence> findByJiraEquivalence(String jiraEquivalence);
	Optional<Equivalence> findByFenixEquivalence(String fenixEquivalence);
}
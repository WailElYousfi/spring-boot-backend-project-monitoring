package com.example.demo.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Feature;


@Repository
public interface FeatureRepository extends JpaRepository<Feature, Integer> {
	Boolean existsByFeatureId(Integer featureId);

}
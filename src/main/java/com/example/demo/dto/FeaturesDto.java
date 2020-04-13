package com.example.demo.dto;

import java.util.Map;


public class FeaturesDto {
	
	public Map<Integer, Integer> featuresAndAccesslevels;
	
	public FeaturesDto() {
		
	}

	public FeaturesDto(Map<Integer, Integer> featuresAndAccesslevels) {
		this.featuresAndAccesslevels = featuresAndAccesslevels;
	}

	public Map<Integer, Integer> getFeaturesAndAccesslevels() {
		return featuresAndAccesslevels;
	}

	public void setFeaturesAndAccesslevels(Map<Integer, Integer> featuresAndAccesslevels) {
		this.featuresAndAccesslevels = featuresAndAccesslevels;
	}
	
	
	
}
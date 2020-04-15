package com.example.demo.dto;

import java.util.Map;


public class FeaturesDto {
	
	public Map<String, Integer> featuresAndAccesslevels;
	
	public FeaturesDto() {
		
	}

	public FeaturesDto(Map<String, Integer> featuresAndAccesslevels) {
		this.featuresAndAccesslevels = featuresAndAccesslevels;
	}

	public Map<String, Integer> getFeaturesAndAccesslevels() {
		return featuresAndAccesslevels;
	}

	public void setFeaturesAndAccesslevels(Map<String, Integer> featuresAndAccesslevels) {
		this.featuresAndAccesslevels = featuresAndAccesslevels;
	}
	
	
	
}
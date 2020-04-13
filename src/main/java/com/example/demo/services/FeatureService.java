package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.demo.models.Feature;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.dao.FeatureRepository;

@Service
public class FeatureService {
     
    @Autowired
    FeatureRepository repository;
     
    public Feature createFeature(Feature feature) {
        return repository.save(feature);
    }

    public Feature updateFeature(Feature feature) {
        Feature featureDb = getFeatureById(feature.getFeatureId());
        Feature featureUpdate = featureDb;
        featureUpdate.setUrl(feature.getUrl());
        featureUpdate.setFeatureName(feature.getFeatureName());
        return repository.save(featureUpdate);
    }
    
    public List <Feature> getAllFeatures() {
        return this.repository.findAll();
    }
    

    public Feature getFeatureById(Integer featureId) {
        Optional <Feature> featureDb = this.repository.findById(featureId);
        if (featureDb.isPresent()) {
            return featureDb.get();
        } else {
            throw new ResourceNotFoundException("feature not found");
        }
    }
    
    public List<Feature> getAllFeaturesByIds(List<Integer> ids){
        List<Feature> featuresDb = this.repository.findAllById(ids);
        return featuresDb;
    }
    

    public void deleteFeature(Integer featureId) {
        Feature featureDb = getFeatureById(featureId);
        this.repository.delete(featureDb);        
    }
}
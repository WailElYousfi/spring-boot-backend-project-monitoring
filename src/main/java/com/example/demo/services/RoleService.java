package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.demo.models.Feature;
import com.example.demo.models.Role;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.dao.RoleRepository;

@Service
public class RoleService {
     
    @Autowired
    RoleRepository repository;
    
    @Autowired
    FeatureService featureService;
     
    public Role createRole(Role role) {
        return repository.save(role);
    }

    public Role updateRole(Role role) {
        Role roleDb = getRoleById(role.getRoleId());
        Role roleUpdate = roleDb;
        roleUpdate.setName(role.getName());
        roleUpdate.setFeatures(role.getFeatures());
        return repository.save(roleUpdate);
    }
    
    public List <Role> getAllRoles() {
        return this.repository.findAll();
    }
    
    public Role updateFeatures(Integer roleId, List<Integer> featureIds) {
        Role roleDb = getRoleById(roleId);
        List<Feature> features = new ArrayList<Feature>();      
        for (Integer featureId : featureIds) {
            Feature feature = this.featureService.getFeatureById(featureId);
            features.add(feature);
        }     
        Role roleUpdate = roleDb;
        roleUpdate.setFeatures(features);
        return repository.save(roleUpdate);
    }

    public Role getRoleById(Integer roleId) {
        Optional <Role> roleDb = this.repository.findById(roleId);
        if (roleDb.isPresent())
            return roleDb.get();
        else
            throw new ResourceNotFoundException("role not found");       
    }
    

    public void deleteRole(Integer roleId) {
        Role roleDb = getRoleById(roleId);
        this.repository.delete(roleDb);
    }
}
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

    
    public List <Role> getAllRoles() {
        return this.repository.findAll();
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
package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.demo.models.Project;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UserRepository;

@Service
public class UserService {
     
    @Autowired
    UserRepository repository;

    @Autowired
    RoleRepository roleRepository;
    
    
    @Autowired
    ProjectService projectService;

	@Autowired
	PasswordEncoder encoder;
     
/*    public User createUser(User user) {
        return repository.save(user);
    }*/

    public User updateUser(User user, Integer roleId, Long userId, List<Integer> projectIds) {
        Optional <User> userDb = this.repository.findById(userId);
        Optional <Role> role = this.roleRepository.findById(roleId);
        
        List<Project> projects = new ArrayList<Project>();
        
        for (Integer projectId : projectIds) {
            Project project = this.projectService.getProjectById(projectId);
            projects.add(project);
        }
        if (userDb.isPresent()) {
            User userUpdate = userDb.get();
            userUpdate.setEmail(user.getEmail());
            userUpdate.setFirstname(user.getFirstname());
            userUpdate.setLastname(user.getLastname());
            userUpdate.setJiraUsername(user.getJiraUsername());
            userUpdate.setPhone(user.getPhone());         
            userUpdate.setRole(role.get());
            userUpdate.setUserCode(user.getUserCode());
            userUpdate.setUsername(user.getUsername());
            userUpdate.setProjects(projects);
            
            repository.save(userUpdate);            
            return userUpdate;
        }else {
        	throw new ResourceNotFoundException("user not found");
        }
    }

    
    public List <User> getAllUser() {
        return this.repository.findAll();
    }
    

    public User getUserById(Long userId) {
        Optional <User> userDb = this.repository.findById(userId);

        if (userDb.isPresent()) {
            return userDb.get();
        } else {
            throw new ResourceNotFoundException("user not found");
        }
    }
    
    public User getUserByUsername(String username) {
        Optional <User> userDb = this.repository.findByUsername(username);

        if (userDb.isPresent()) {
            return userDb.get();
        } else {
            throw new ResourceNotFoundException("user not found");
        }
    }
    
    public User getUserByJiraUsername(String jiraUsername) {
        Optional <User> userDb = this.repository.findByJiraUsername(jiraUsername);

        if (userDb.isPresent()) {
            return userDb.get();
        } else {
            throw new ResourceNotFoundException("jiraUsername not found");
        }
    }
    

    public void deleteUser(Long userId) {
        Optional <User> userDb = this.repository.findById(userId);

        if (userDb.isPresent()) {
            this.repository.delete(userDb.get());
        } else {
            throw new ResourceNotFoundException("user not found");
        }

    }
}
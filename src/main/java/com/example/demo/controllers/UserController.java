package com.example.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;
    
    @Autowired
    AuthController authController;

    @GetMapping("/users/all")
    public ResponseEntity < List<User> > getAllUser() {
        return ResponseEntity.ok().body(userService.getAllUser());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity <User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping("/users/all")
    public ResponseEntity <User> createUser(@Valid @RequestBody SignupRequest signUpRequest) {
        authController.registerUser(signUpRequest);
        return ResponseEntity.ok().body(userService.getUserByUsername(signUpRequest.getUsername()));
    }

    @PutMapping("/roles/{roleId}/users/{userId}")
    public ResponseEntity <User> updateUser(@Valid @RequestBody User user, @PathVariable Integer roleId, @PathVariable Long userId,  @RequestParam List<Integer> projectIds) {
    	return ResponseEntity.ok().body(this.userService.updateUser(user, roleId, userId, projectIds));
    }

    @DeleteMapping("/users/{id}")
    public HttpStatus deleteUser(@PathVariable Long id) {
        this.userService.deleteUser(id);
        return HttpStatus.OK;
    }
    
    @GetMapping("/users/{id}/archive")
    public ResponseEntity <User> archiveUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.userService.archiveUser(id));
    }
}
package com.example.demo.services;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.UserRepository;
import com.example.demo.models.User;

public class test {
	@Autowired
	static UserRepository rep ;
	
	public static void main(String[] args) {

		Optional<User> user = rep.findByUsername("new");
		if(user.isPresent())
			System.out.println(user.get().getFirstname());
	}

}

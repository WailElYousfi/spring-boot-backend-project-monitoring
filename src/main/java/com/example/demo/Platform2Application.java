package com.example.demo;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Platform2Application {

	public static void main(String[] args) {
		new File(System.getProperty("user.dir")+"/src/main/resources/files/templates").mkdir();
		new File(System.getProperty("user.dir")+"/src/main/resources/files/incidencias").mkdir();
		new File(System.getProperty("user.dir")+"/src/main/resources/files/requerimientos").mkdir();
		new File(System.getProperty("user.dir")+"/src/main/resources/files/accs").mkdir();
		SpringApplication.run(Platform2Application.class, args);
	}

}

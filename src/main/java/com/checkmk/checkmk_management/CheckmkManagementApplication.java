package com.checkmk.checkmk_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
public class CheckmkManagementApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(CheckmkManagementApplication.class, args);
	}

}

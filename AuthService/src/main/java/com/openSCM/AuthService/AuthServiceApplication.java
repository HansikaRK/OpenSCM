package com.openSCM.AuthService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Remove the below import when database are used
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//Remove the exlude part when the databases are used
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

}

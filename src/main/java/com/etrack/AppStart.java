package com.etrack;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:AppConfiguration.test.xml")
public class AppStart {

	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(AppStart.class, args);	
	}
	

}	

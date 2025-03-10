package com.skiply.studentManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StudentManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementServiceApplication.class, args);
	}

}

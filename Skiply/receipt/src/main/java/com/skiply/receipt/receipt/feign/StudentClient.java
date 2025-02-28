package com.skiply.receipt.receipt.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.skiply.receipt.receipt.model.Student;

@FeignClient(name = "student-service", url = "http://localhost:8081")
public interface StudentClient {
	
	@GetMapping("/student/{studentId}")
	ResponseEntity<Student> getStudentById(@PathVariable Long studentId);

}
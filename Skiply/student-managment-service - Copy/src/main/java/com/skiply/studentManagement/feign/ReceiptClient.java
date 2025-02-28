package com.skiply.studentManagement.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.skiply.studentManagement.config.FeignConfig;
import com.skiply.studentManagement.model.Receipt;

@Component
@FeignClient(name = "receipt", url="http://localhost:8083", configuration=FeignConfig.class)
public interface ReceiptClient {
	
	@PostMapping(value = "/receipt")
	public Receipt saveReceipt(@RequestBody Receipt receipt);
	
	@GetMapping(value = "/receipt/{studentId}")
	public ResponseEntity<?> viewReceipt(@PathVariable Long studentId);
}

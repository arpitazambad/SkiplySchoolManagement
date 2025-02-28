package com.skiply.studentManagement.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skiply.studentManagement.exception.CircuitBreakerException;
import com.skiply.studentManagement.exception.FeeAlreadyPaidException;
import com.skiply.studentManagement.exception.StudentNotFoundException;
import com.skiply.studentManagement.feign.ReceiptClient;
import com.skiply.studentManagement.model.Fee;
import com.skiply.studentManagement.model.Receipt;
import com.skiply.studentManagement.model.Student;
import com.skiply.studentManagement.repository.StudentRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class FeeService {

	StudentRepository studentRepository;
	
	ReceiptClient receiptClient;
	
	@Autowired(required=true)
	FeeService(StudentRepository studentRepository,ReceiptClient receiptClient){
		this.studentRepository= studentRepository;
		this.receiptClient= receiptClient;
	}
	
	@CircuitBreaker(name = "receiptService", fallbackMethod = "fallbackForReceiptService")
	public Receipt callReceiptService(Receipt receipt) {
		Receipt savedReceipt = receiptClient.saveReceipt(receipt);
		return savedReceipt;
	}

	public String fallbackForReceiptService(Throwable throwable) {
		throw new CircuitBreakerException("Receipt Service is down");
	}

	public Receipt collectFee(Long studentId, Fee fee) {
		Optional<Student> studentData = studentRepository.findById(studentId); 
		
		if(! studentData.isPresent()) {
			throw new StudentNotFoundException("Invalid Student Id");
		}
		if (studentData.get().getIsFeePaid().booleanValue()) {
			throw new FeeAlreadyPaidException("Fee already paid for student id : " + studentId);
		}
		else {
			Receipt receipt = new Receipt();
			receipt.setCardNumber(fee.getCardNumber());
			receipt.setCardType(fee.getCardType());
			receipt.setDateTime(LocalDateTime.now());
			receipt.setFee(fee.getFee());
			receipt.setStudentId(studentId);
			Receipt savedReceipt = callReceiptService(receipt);
			if(savedReceipt != null) {
				studentRepository.updateFeePaid(studentId, true);
			}
			return savedReceipt;

		} 
		
	}
}

package com.skiply.receipt.receipt.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skiply.receipt.receipt.exception.ReceiptNotFoundException;
import com.skiply.receipt.receipt.feign.StudentClient;
import com.skiply.receipt.receipt.model.Receipt;
import com.skiply.receipt.receipt.model.ReceiptResponse;
import com.skiply.receipt.receipt.model.Student;
import com.skiply.receipt.receipt.repository.ReceiptRepository;

@Service
public class ReceiptService {
	ReceiptRepository receiptRepository;
	StudentClient studentClient;
	
	ReceiptService(ReceiptRepository receiptRepository, StudentClient studentClient){
		this.receiptRepository= receiptRepository;
		this.studentClient= studentClient;
	}
	
	public Receipt saveReceipt(Receipt receipt) {
		return receiptRepository.save(receipt);
	}

	public ReceiptResponse viewReceiptByStudentId(Long studentId) {
		Receipt receipt = receiptRepository.findFirstByStudentId(studentId);
		if(receipt == null) {
			throw new ReceiptNotFoundException("No receipt found for student ID: " + studentId );
		}
			ReceiptResponse receiptResponse = new ReceiptResponse();
			Student student = new Student();
			ResponseEntity<Student> studentById = studentClient.getStudentById(studentId);
			ModelMapper modelmapper = new ModelMapper();
			modelmapper.map(studentById.getBody(),student);
			receiptResponse.setCardNumber(receipt.getCardNumber());
			receiptResponse.setReferenceNumber(receipt.getReferenceNumber());
			receiptResponse.setCardType(receipt.getCardType());
			receiptResponse.setDateTime(receipt.getDateTime());
			receiptResponse.setFee(receipt.getFee());
			receiptResponse.setStudent(student);
			return receiptResponse;
		
	}


}

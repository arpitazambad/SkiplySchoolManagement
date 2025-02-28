package com.skiply.receipt.receipt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skiply.receipt.receipt.model.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
	
	Receipt findFirstByStudentId(Long studentId);

}

package com.skiply.studentManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Student {
   
    @Id
    @GeneratedValue
    private Long id;
    private String firstname;
    private String lastname;
    private String grade;
	private Long mobileNumber;
	private String schoolName;
	private Integer schoolId;
	private Long receiptNumber;
	private Boolean isFeePaid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public Long getReceiptNumber() {
		return receiptNumber;
	}
	public void setReceiptNumber(Long receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public Boolean getIsFeePaid() {
		return isFeePaid;
	}
	public void setIsFeePaid(Boolean isFeePaid) {
		this.isFeePaid = isFeePaid;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", grade=" + grade
				+ ", mobileNumber=" + mobileNumber + ", schoolName=" + schoolName + ", schoolId=" + schoolId
				+ ", receiptNumber=" + receiptNumber + ", IsFeePaid=" + isFeePaid + "]";
	}
	
	
}
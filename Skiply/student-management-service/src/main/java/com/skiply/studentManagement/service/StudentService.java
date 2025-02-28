package com.skiply.studentManagement.service;

import org.springframework.stereotype.Service;

import com.skiply.studentManagement.model.Student;
import com.skiply.studentManagement.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

	private StudentRepository studentRepository;
	
	StudentService(StudentRepository studentRepository){
		this.studentRepository=studentRepository;
	}
	

	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}

	public Optional<Student> getStudentById(Long id) {
		return studentRepository.findById(id);
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public Student updateStudent(Long id, Student student) {
        student.setId(id);
		return studentRepository.save(student);
	}

	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
	}
	
	public void deleteAllStudent() {
		studentRepository.deleteAll();
	}
}
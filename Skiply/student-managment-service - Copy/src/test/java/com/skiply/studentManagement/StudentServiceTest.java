package com.skiply.studentManagement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.skiply.studentManagement.model.Student;
import com.skiply.studentManagement.repository.StudentRepository;
import com.skiply.studentManagement.service.StudentService;

@SpringBootTest
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    @BeforeEach
    public void setUp() {
        // Create a mock student object
         student = new Student();
         student.setFirstname("John");
         student.setLastname( "Doe");
         student.setId(1l);
         student.setIsFeePaid(false);
         student.setMobileNumber(12909l);

        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddStudent() {
        // Arrange
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // Act
        Student createdStudent = studentService.addStudent(student);

        // Assert
        assertNotNull(createdStudent);
        assertEquals("John", createdStudent.getFirstname());
        assertEquals(1l, createdStudent.getId());
        assertEquals(12909l, createdStudent.getMobileNumber());

        // Verify that save was called once with the student object
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testGetStudentById() {
        // Arrange
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        // Act
        Optional<Student> foundStudent = studentService.getStudentById(1L);

        // Assert
        assertEquals("John", foundStudent.get().getFirstname());

        // Verify that findById was called once with the correct id
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void testGetStudentByIdNotFound() {
        // Arrange
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());

        // Act
        Optional<Student> foundStudent = studentService.getStudentById(99L);

        // Verify that findById was called once with the correct id
        verify(studentRepository, times(1)).findById(99L);
    }

    @Test
    void testGetAllStudents() {
        // Arrange
        when(studentRepository.findAll()).thenReturn(List.of(student));

        // Act
        List<Student> students = studentService.getAllStudents();

        // Assert
        assertNotNull(students);
        assertEquals(1, students.size());
        assertEquals("John", students.get(0).getFirstname());

        // Verify that findAll was called once
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testDeleteStudent() {
        // Arrange
        doNothing().when(studentRepository).deleteById(1L);

        // Act
        studentService.deleteStudent(1L);

        // Assert
        verify(studentRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteAllStudents() {
        // Arrange
        doNothing().when(studentRepository).deleteAll();

        // Act
        studentService.deleteAllStudent();

        // Assert
        verify(studentRepository, times(1)).deleteAll();
    }

    @Test
    void testUpdateStudent() {
        // Arrange
        Student updatedStudent = new Student();
        updatedStudent.setFirstname("Jane");
        updatedStudent.setMobileNumber(12345l);

        // Assuming the student already exists with id 1L
        when(studentRepository.save(any(Student.class))).thenReturn(updatedStudent);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        // Act
        Student result = studentService.updateStudent(1L, updatedStudent);

        // Assert
        assertNotNull(result);
        assertEquals("Jane", result.getFirstname());
        assertEquals(12345l, result.getMobileNumber());

        // Verify that save was called once with the updated student
        verify(studentRepository, times(1)).save(updatedStudent);
    }
}
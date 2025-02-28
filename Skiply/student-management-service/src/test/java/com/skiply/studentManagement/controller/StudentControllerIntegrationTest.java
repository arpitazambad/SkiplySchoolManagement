package com.skiply.studentManagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skiply.studentManagement.model.Student;
import com.skiply.studentManagement.service.StudentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentService studentService;

    private Student student;

    @BeforeEach
    public void setUp() {
        studentService.deleteAllStudent();;  // Clean up DB before each test
        student = new Student();
        student.setFirstname("John");
        student.setLastname( "Doe");
        student.setIsFeePaid(false);
        student.setMobileNumber(12909l);
    }

    @Test
    void testAddStudent() throws Exception {
        mockMvc.perform(post("/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2l));
        
    }

    @Test
    void testGetStudent() throws Exception {
        Student savedStudent = studentService.addStudent(student);  // Save student before test

        mockMvc.perform(get("/student/{id}", savedStudent.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1l))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mobileNumber").value(12909l));
    }

    @Test
    void testGetStudentNotFound() throws Exception {
        mockMvc.perform(get("/student/{id}", 999L))  // Non-existing ID
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetAllStudents() throws Exception {
        studentService.addStudent(student);  // Add a student for testing

        mockMvc.perform(get("/student/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstname").value("John"));
    }

    @Test
    void testDeleteStudent() throws Exception {
        Student savedStudent = studentService.addStudent(student);  // Add a student for deletion

        mockMvc.perform(delete("/student/{id}", savedStudent.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        mockMvc.perform(get("/student/{id}", savedStudent.getId()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

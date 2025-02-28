package com.skiply.studentManagement.repository;

import com.skiply.studentManagement.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllBySchoolId(Integer schoolId);
    
    @Transactional
	@Modifying
    @Query("update Student s set s.isFeePaid = ?2 where id = ?1")
	void updateFeePaid(Long studentId, Boolean val);
}

package org.hbrs.se2.project.hellocar.repository;

import org.hbrs.se2.project.hellocar.dtos.StudentDTO;
import org.hbrs.se2.project.hellocar.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("  SELECT new org.hbrs.se2.project.hellocar.dtos.StudentDTO(firstName,lastName) FROM Student  WHERE id = ?1")
    StudentDTO findStudent(int userid);
}

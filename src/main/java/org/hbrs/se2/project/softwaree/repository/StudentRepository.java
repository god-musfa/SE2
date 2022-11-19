package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.StudentDTO;
import org.hbrs.se2.project.softwaree.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("  SELECT new org.hbrs.se2.project.softwaree.dtos.StudentDTO(id,firstName,lastName,semester,birthday,degree,university,subject) FROM Student  WHERE id = ?1")
    StudentDTO findStudent(int userid);

    @Query("  SELECT new org.hbrs.se2.project.softwaree.dtos.StudentDTO(firstName,lastName,semester,birthday) FROM Student  WHERE id = ?1")
    StudentDTO findStudentWithBirthdayAndSemester(int userid);
}

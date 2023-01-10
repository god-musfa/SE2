package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.StudentDTO;
import org.hbrs.se2.project.softwaree.entities.Skill;
import org.hbrs.se2.project.softwaree.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import java.util.Set;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("  SELECT new org.hbrs.se2.project.softwaree.dtos.StudentDTO(firstName,lastName,semester,birthday) FROM Student  WHERE id = ?1")
    StudentDTO findStudentWithBirthdayAndSemester(int userid);

    @Query("select s from Student s where s.id = ?1")
    Optional<Student> findStudentById(Integer id);

    @Query("select new org.hbrs.se2.project.softwaree.dtos.StudentDTO(s.id,s.firstName,s.lastName,s.semester,s.birthday,s.degree,s.university,s.subject) from Student s where s.id= ?1 ")
    Optional<StudentDTO> findFullStudentByID(Integer id);

    @Query("select s.skills from Student s where s.id = ?1")
    Optional<Set<Skill>> getSkillsByStudentID(Integer id);
}

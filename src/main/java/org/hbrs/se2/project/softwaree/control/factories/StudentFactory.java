package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.dtos.StudentDTO;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.entities.Student;

import java.util.Optional;

public class StudentFactory {
    public static Student createStudent(StudentDTO studentDTO){
        Student student = new Student();

        student.setLastName(studentDTO.getLastName());
        student.setFirstName(studentDTO.getFirstName());
        student.setBirthday(studentDTO.getBirthday());
        student.setSemester(studentDTO.getSemester());
        student.setDegree((studentDTO.getDegree()));
        student.setSubject(studentDTO.getSubject());
        student.setUniversity(studentDTO.getUniversity());
        student.setId(studentDTO.getId());
        student.setSkills(studentDTO.getSkills());

        return student;
    }

    public static StudentDTO createDTO(Student student) {
        StudentDTO newStudentDTO = new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getSemester(),
                student.getBirthday(),
                student.getDegree(),
                student.getUniversity(),
                student.getSubject()
        );
        newStudentDTO.setSkills(student.getSkills());

        return newStudentDTO;
    }
}

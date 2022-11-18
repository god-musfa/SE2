package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.dtos.StudentDTO;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.entities.Student;

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

        return student;
    }
}

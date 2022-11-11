package org.hbrs.se2.project.hellocar.control;

import org.hbrs.se2.project.hellocar.dtos.StudentDTO;
import org.hbrs.se2.project.hellocar.dtos.UserDTO;
import org.hbrs.se2.project.hellocar.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EditProfileControl {
    @Autowired
    StudentRepository repo;

    public StudentDTO getStudentFromUser(UserDTO userDTO) {
        return repo.findStudent(userDTO.getId());
    }
}

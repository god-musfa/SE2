package org.hbrs.se2.project.softwaree.control;

import org.hbrs.se2.project.softwaree.dtos.StudentDTO;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditProfileControl {
    @Autowired
    StudentRepository repo;

    public StudentDTO getStudentFromUser(UserDTO userDTO) {
        return repo.findStudent(userDTO.getId());
    }
}

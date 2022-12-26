package org.hbrs.se2.project.softwaree.test.control;

import org.hbrs.se2.project.softwaree.control.StudentListControl;
import org.hbrs.se2.project.softwaree.dtos.StudentDTO;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentListControlTest {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentListControl studentListControl;

    @Test
    void findAllDTO() {
        List<StudentDTO> studentDTOS = studentListControl.findAllDTO();
        assertFalse(studentDTOS.isEmpty());
        assertEquals(studentDTOS.size() , studentRepository.findAll().size());
    }

    @Test
    void getUserType() {

        UserDTO userDTO = null;
        assertSame(StudentListControl.UserType.UNKNOWN , studentListControl.getUserType(userDTO));
        userDTO = new UserDTO();
        userDTO.setUserType("unknown");
        assertSame(StudentListControl.UserType.UNKNOWN , studentListControl.getUserType(userDTO));
        userDTO.setUserType("student");
        assertSame(StudentListControl.UserType.STUDENT , studentListControl.getUserType(userDTO));
        userDTO.setUserType("company");
        assertSame(StudentListControl.UserType.COMPANY , studentListControl.getUserType(userDTO));

    }

    @Test
    void testFindAllDTO() {
        List<StudentDTO> studentDTOS = studentListControl.findAllDTO("sascha");
        assertFalse(studentDTOS.isEmpty());
        assertEquals(studentDTOS.size() , 1);
    }
}
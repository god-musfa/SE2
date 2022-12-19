package org.hbrs.se2.project.softwaree.control;

import org.hbrs.se2.project.softwaree.dtos.StudentDTO;
import org.hbrs.se2.project.softwaree.entities.Student;
import org.hbrs.se2.project.softwaree.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static org.hbrs.se2.project.softwaree.control.factories.StudentFactory.createDTO;

@Component
public class StudentListControl {

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> findAllDTO(){
        List<Student> studentList = studentRepository.findAll();
        List<StudentDTO> studentDTOList = new ArrayList<>();

        for (Student s : studentList){
            studentDTOList.add(createDTO(s));
        }

        return studentDTOList;
    }

    public List<StudentDTO> findAllDTO(String filter){
        List<StudentDTO> studentDTOList = this.findAllDTO();

        if (filter == null || filter.isEmpty()){
          return studentDTOList;
        }

        String[] filterList = filter.split(" ");

        List<StudentDTO> filteredStudentDTO = new ArrayList<>();
        for (StudentDTO studentDTO : studentDTOList) {

            boolean containsAll = true;
            for (String tmpFilter : filterList) {
                if (!filtering(studentDTO, tmpFilter)) {
                    containsAll = false;
                }
            }

            if (containsAll) {
                filteredStudentDTO.add(studentDTO);
            }
        }

        return filteredStudentDTO;
    }

    private boolean filtering(StudentDTO term, String filter){
        boolean contains = searchInTerm(term.getFirstName(), filter);  //whole name search for convenience
        contains |= searchInTerm(term.getLastName(), filter); //backwards whole name search for convenience
        contains |= searchInTerm(term.getSubject(), filter);
        contains |= searchInTerm(term.getDegree(), filter);
        contains |= searchInTerm(term.getUniversity(), filter);
        contains |= term.getSkills().stream().map(skill -> searchInTerm(skill.getDescription(), filter)).reduce(false, (a, b) -> a || b);  //map on true or false, then OR them together

        return contains;
    }

    private boolean searchInTerm(@Nullable String term, String search){
        return nullToEmpty(term).toLowerCase().contains(nullToEmpty(search).toLowerCase());
    }

    private String nullToEmpty(String s){
        return s == null ? "" : s;
    }
}

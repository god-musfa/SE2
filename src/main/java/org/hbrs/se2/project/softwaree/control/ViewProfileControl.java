package org.hbrs.se2.project.softwaree.control;


import org.hbrs.se2.project.softwaree.control.factories.*;
import org.hbrs.se2.project.softwaree.dtos.*;
import org.hbrs.se2.project.softwaree.entities.Company;
import org.hbrs.se2.project.softwaree.entities.StudentRating;
import org.hbrs.se2.project.softwaree.entities.Student;
import org.hbrs.se2.project.softwaree.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ViewProfileControl implements RatingFeedbackControl{
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CompanyRepository companyRepo;
    @Autowired
    SkillRepository skillRepo;
    @Autowired
    StudentRatingRepository ratingRepo;

    @Override
    public void setRating(int rating, int student_id, int company_id, boolean studentRatesCompany) {
        Optional<StudentRatingDTO> currentRating = ratingRepo.findByIDs(student_id, company_id);

        if (currentRating.isPresent()) {
            // Overwrite existing rating:
            StudentRatingDTO currentDTO = currentRating.get();
            currentDTO.setRating(rating);
            StudentRating saveStudentRating = RatingFactory.createRating(currentDTO);
            ratingRepo.save(
                    saveStudentRating
            );
        } else {
            // Create new rating:

            // Get student and company:
            Optional<StudentDTO> studentDTO = studentRepository.findFullStudentByID(student_id);
            Optional<CompanyDTO> companyDTO = companyRepo.getCompanyDTOByID(company_id);

            if (studentDTO.isPresent() && companyDTO.isPresent()) {
                StudentRatingIDDTO newStudentRatingIDDTO = new StudentRatingIDDTO(studentDTO.get(), companyDTO.get());
                StudentRatingDTO newStudentRatingDTO = new StudentRatingDTO(newStudentRatingIDDTO, rating, studentDTO.get(), companyDTO.get());
                StudentRating saveStudentRating = RatingFactory.createRating(newStudentRatingDTO);
                ratingRepo.save(
                        saveStudentRating
                );
            }
        }
    }

    @Override
    public int getRating(int student_id, int company_id, boolean studentRatesCompany) {
        Optional<Student> speculativeStudent = studentRepository.findStudentById(student_id);
        Optional<Company> speculativeCompany = companyRepo.findById(company_id);

        if (speculativeStudent.isPresent() && speculativeCompany.isPresent()) {
            StudentDTO tmpStudentDTO = StudentFactory.createDTO(speculativeStudent.get());
            CompanyDTO tmpCompanyDTO = CompanyFactory.createDTO(speculativeCompany.get());

            StudentRatingIDDTO tmpID = new StudentRatingIDDTO(
                    tmpStudentDTO,
                    tmpCompanyDTO
            );

            // Now get the StudentRating using the StudentRatingID:
            Optional<StudentRating> speculativeRating = ratingRepo.getRatingByID(RatingIDFactory.createRatingID(tmpID));
            if (speculativeRating.isPresent()) {
                return speculativeRating.get().getRating();
            }
        }

        return 0;
    }

    public enum UserType {
        UNKNOWN,
        STUDENT,
        COMPANY
    }


    public StudentDTO getStudentFromUser(UserDTO userDTO) {
        Optional<Student> studentFromDB = studentRepository.findStudentById(userDTO.getId());
        if (studentFromDB.isPresent()) {
            return StudentFactory.createDTO(studentFromDB.get());
        } else {
            return null;
        }
    }

    public CompanyDTO getCompanyFromUser(UserDTO userDTO) {
        Optional<Company> companyFromDB = companyRepo.findById(userDTO.getId());
        if (companyFromDB.isPresent()) {
            return CompanyFactory.createDTO(companyFromDB.get());
        } else {
            return null;
        }
    }


    @Autowired
    AddressRepository addressRepository;
    public AddressDTO getAdressFromUser(UserDTO userDTO){
        return addressRepository.findAdress(userDTO.getAddressId());
    }

    public List<SkillDTO> getStudentSkills(UserDTO userDTO) {
        Optional<Student> targetStudent = studentRepository.findStudentById(userDTO.getId());
        if (targetStudent.isPresent()) {
            return targetStudent.get().getSkills().stream()
                    .map(SkillFactory::getDTO)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();       // Return empty list, if user cannot be found.
        }
    }

    public UserDTO getUserByID(int id) {
        return userRepository.findUserByID(id);
    }

    public AddressDTO getAddressOfUser(UserDTO user) {
        Optional<AddressDTO> speculativeAddress = Optional.of(addressRepository.findAdress(user.getAddressId()));
        if (speculativeAddress.isPresent()) {
            return speculativeAddress.get();
        } else {
            return null;
        }
    }

    public boolean validateProfileID(int profileID) {
        return userRepository.existsById(profileID);
    }

    public UserType getUserType(UserDTO user) {
        if (user.getUserType().equals("student")) {
            return UserType.STUDENT;
        }

        if (user.getUserType().equals("company")) {
            return UserType.COMPANY;
        }

        return UserType.UNKNOWN;

    }




}

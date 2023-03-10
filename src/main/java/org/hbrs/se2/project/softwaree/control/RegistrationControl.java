package org.hbrs.se2.project.softwaree.control;

import org.hbrs.se2.project.softwaree.control.factories.AddressFactory;
import org.hbrs.se2.project.softwaree.control.factories.CompanyFactory;
import org.hbrs.se2.project.softwaree.control.factories.StudentFactory;
import org.hbrs.se2.project.softwaree.control.factories.UserFactory;
import org.hbrs.se2.project.softwaree.dtos.AddressDTO;
import org.hbrs.se2.project.softwaree.dtos.CompanyDTO;
import org.hbrs.se2.project.softwaree.dtos.StudentDTO;
import org.hbrs.se2.project.softwaree.dtos.UserDTO;
import org.hbrs.se2.project.softwaree.entities.Address;
import org.hbrs.se2.project.softwaree.entities.Company;
import org.hbrs.se2.project.softwaree.entities.Student;
import org.hbrs.se2.project.softwaree.entities.User;
import org.hbrs.se2.project.softwaree.repository.AddressRepository;
import org.hbrs.se2.project.softwaree.repository.CompanyRepository;
import org.hbrs.se2.project.softwaree.repository.StudentRepository;
import org.hbrs.se2.project.softwaree.repository.UserRepository;
import org.hbrs.se2.project.softwaree.security.SecurityHandler;
import org.hbrs.se2.project.softwaree.util.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class RegistrationControl {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CompanyRepository companyRepository;

    private List<UserDTO> userDTOList = new ArrayList<>();

    public boolean save(UserDTO userDTO, StudentDTO studentDTO, AddressDTO addressDTO){
        if (emailExists(userDTO.getEmail())){
            System.out.println("Email is already taken!");
            return false;
        }

        Address address = AddressFactory.createAddress(addressDTO);
        addressRepository.save(address);
        userDTO.setProfilePic(Globals.DEFAULT_PROFILE_PICTURE);
        User user = UserFactory.createUser(userDTO, address);

        // Hash user password before saving:
        user.setPassword(
                SecurityHandler.hashPassword(user.getPassword())
        );

        Student student = StudentFactory.createStudent(studentDTO);
        user.setStudent(student);
        student.setUser(user);
        studentRepository.save(student);
        userRepository.save(user);


        return true;
    }
    public boolean saveC(UserDTO userDTO, CompanyDTO companyDTO, AddressDTO addressDTO){
        if (emailExists(userDTO.getEmail())){
            System.out.println("Email is already taken!");
            return false;
        }

        Address address = AddressFactory.createAddress(addressDTO);
        addressRepository.save(address);
        userDTO.setProfilePic(Globals.DEFAULT_PROFILE_PICTURE);
        User user = UserFactory.createUser(userDTO, address);
        user.setPassword(
                SecurityHandler.hashPassword(user.getPassword())
        );
        Company company = CompanyFactory.createCompany(companyDTO);
        user.setCompany(company);
        company.setUser(user);
        companyRepository.save(company);
        userRepository.save(user);

        return true;
    }

    public boolean emailExists(String email){
        return userRepository.checkEmailExists(email);
    }



}

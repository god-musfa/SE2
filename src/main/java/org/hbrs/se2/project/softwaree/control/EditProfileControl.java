package org.hbrs.se2.project.softwaree.control;

import org.hbrs.se2.project.softwaree.control.factories.*;
import org.hbrs.se2.project.softwaree.dtos.*;
import org.hbrs.se2.project.softwaree.entities.*;
import org.hbrs.se2.project.softwaree.repository.*;
import org.hbrs.se2.project.softwaree.util.ProfilePictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class EditProfileControl implements PictureUploadController{
    @Autowired
    StudentRepository repo;
    @Autowired
    UserRepository repoU;

    @Autowired
    SkillRepository skillRepo;

    @Autowired
    CompanyRepository compRepo;

    @Autowired
    BlacklistRepository blacklistRepo;

    @Autowired
    AddressRepository addressRepo;

    public StudentDTO getStudentFromUser(UserDTO userDTO) {
        Optional<Student> studentFromDB = repo.findStudentById(userDTO.getId());
        if (studentFromDB.isPresent()) {
            return StudentFactory.createDTO(studentFromDB.get());
        } else {
            return null;
        }
    }

    @Autowired
    AddressRepository repoA;
    public AddressDTO getAdressFromUser(UserDTO userDTO){
        return repoA.findAdress(userDTO.getAddressId());
    }

    @Autowired
    CompanyRepository repoC;
    public CompanyDTO getCompanyFromUser(UserDTO userDTO) {
        return repoC.findCompany(userDTO.getId());
    }

    public void createStudent(StudentDTO studentDTO){
        Student studentEntity = StudentFactory.createStudent(studentDTO);
        repo.save(studentEntity);
    }

    public void createAddress(AddressDTO addressDTO, UserDTO userDTO){
        Address addressEntity = AddressFactory.createAddress(addressDTO);
        repoA.save(addressEntity);
    }

    public void createCompany(CompanyDTO companyDTO){
        Company companyEntity = CompanyFactory.createCompany(companyDTO);
        repoC.save(companyEntity);
    }

    public List<SkillDTO> getAvailableSkills() {
        return skillRepo.findAll().stream()
                .map(SkillFactory::getDTO)
                .collect(Collectors.toList());
    }

    public List<CompanyDTO> getBlockedCompanys(Integer userid) {
        List<BlacklistDTO> bList = blacklistRepo.findBlockedCompanys(userid);
        List<CompanyDTO> cList = new ArrayList<>();
        for (BlacklistDTO blacklistDTO : bList){
            cList.add(compRepo.findCompany(blacklistDTO.getCompanyID()));
        }
        return cList;
    }

    public List<Integer> getBlockedCompanyIDs(Integer userid) {
        List<BlacklistDTO> bList = blacklistRepo.findBlockedCompanys(userid);
        List<Integer> intList = new ArrayList<>();
        for (BlacklistDTO blacklistDTO : bList){
            intList.add(blacklistDTO.getCompanyID());
        }
        return intList;
    }

    public List<SkillDTO> getStudentSkills(UserDTO userDTO) {
        Optional<Student> targetStudent = repo.findStudentById(userDTO.getId());
        if (targetStudent.isPresent()) {
            return targetStudent.get().getSkills().stream()
                    .map(SkillFactory::getDTO)
                    .collect(Collectors.toList());
        } else {
            System.out.println(String.format("Cannot get skills for user with id %d", userDTO.getId()));
            return new ArrayList<>();       // Return empty list, if user cannot be found.
        }
    }

    public Set<Skill> createSkillSet(Set<String> skillNames) {
        Set<Skill> returnSet = new HashSet<>();

        for (String skillName : skillNames) {
            Optional<Skill> skillFromDB = skillRepo.findByDescription(skillName);
            if (skillFromDB.isPresent()) {
                // Take Skill from DB and add to list:
                returnSet.add(skillFromDB.get());
            } else {
                // Create empty skill and add description from combobox:
                Skill newSkill = new Skill();
                newSkill.setDescription(skillName);
                returnSet.add(newSkill);
            }
        }

        return returnSet;
    }

    public Set<Company> createCompanySet(Set<CompanyDTO> companys) {
        Set<Company> returnSet = new HashSet<>();

        for (CompanyDTO company : companys) {
            Optional<Company> companyFromDB = compRepo.findById(company.getId());
            if (companyFromDB.isPresent()) {
                // Take from DB and add to list:
                returnSet.add(companyFromDB.get());
            }
        }
        return returnSet;
    }

    public void saveSkill(Skill skill) {
        skillRepo.save(skill);
    }

    public void removeCompFromBlacklist(Integer userID, Integer companyID) {
        blacklistRepo.delete(BlacklistFactory.getBlacklistEntity(blacklistRepo, userID,companyID));
    }

    @Transactional
    public void deleteAccount(UserDTO user){
        repoU.deleteUser(user.getId());
        repoA.deleteById(user.getAddressId());
    }


    @Override
    public boolean setProfilePicture(UserDTO targetUser, byte[] imageData, String mimeType) {
        // Set image for current user:
        String srcData = String.format("data:%s;base64, %s",  mimeType, ProfilePictureService.toBase64(imageData));

        Optional<User> dbUser = repoU.findById(targetUser.getId());
        if (dbUser.isPresent()) {
            dbUser.get().setProfilePic(srcData);
            repoU.save(dbUser.get());
            targetUser.setProfilePic(srcData);          // To ensure that the current user object is up to date.
            return true;
        } else {
            return false;
        }
    }
}

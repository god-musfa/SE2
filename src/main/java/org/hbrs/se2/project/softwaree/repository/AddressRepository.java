package org.hbrs.se2.project.softwaree.repository;

import org.hbrs.se2.project.softwaree.dtos.AddressDTO;
import org.hbrs.se2.project.softwaree.dtos.StudentDTO;
import org.hbrs.se2.project.softwaree.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query("  SELECT new org.hbrs.se2.project.softwaree.dtos.AddressDTO(a.id,a.street, a.number, a.postalCode, a.city)" +
            " FROM Address a")
    List<AddressDTO> findAllAddress();

    @Query("  SELECT new org.hbrs.se2.project.softwaree.dtos.AddressDTO(a.id,a.street, a.number, a.city,a.postalCode)" +
                    " FROM Address a WHERE a.id = ?1" )
    AddressDTO findAdress(int userid);
}
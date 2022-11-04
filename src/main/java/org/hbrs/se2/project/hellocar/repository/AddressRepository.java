package org.hbrs.se2.project.hellocar.repository;

import org.hbrs.se2.project.hellocar.dtos.AddressDTO;
import org.hbrs.se2.project.hellocar.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query("  SELECT new org.hbrs.se2.project.hellocar.dtos.AddressDTO(a.id,a.street, a.number, a.postalCode, a.city)" +
            " FROM Address a")
    List<AddressDTO> findAllAddress();
}
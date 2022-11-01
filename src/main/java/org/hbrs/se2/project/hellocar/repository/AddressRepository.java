package org.hbrs.se2.project.hellocar.repository;

import org.hbrs.se2.project.hellocar.dtos.AddressDTO;
import org.hbrs.se2.project.hellocar.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query("  SELECT a.street, a.number, a.postalCode, a.city" +
            " FROM Address a")
    List<AddressDTO> findAllAddress();
}
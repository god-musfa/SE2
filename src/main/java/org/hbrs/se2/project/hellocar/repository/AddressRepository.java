package org.hbrs.se2.project.hellocar.repository;

import org.hbrs.se2.project.hellocar.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
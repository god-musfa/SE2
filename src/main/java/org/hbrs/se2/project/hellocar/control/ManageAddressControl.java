package org.hbrs.se2.project.hellocar.control;

import org.hbrs.se2.project.hellocar.control.factories.AddressFactory;
import org.hbrs.se2.project.hellocar.dtos.AddressDTO;
import org.hbrs.se2.project.hellocar.dtos.UserDTO;
import org.hbrs.se2.project.hellocar.entities.Address;
import org.hbrs.se2.project.hellocar.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ManageAddressControl {
    @Autowired
    private AddressRepository repository;


    public void createAddress(AddressDTO addressDTO , UserDTO userDTO ) {
        // Hier könnte man noch die Gültigkeit der Daten überprüfen
        // check( carDTO );

        //Erzeuge ein neues Car-Entity konsistent über eine Factory
        Address addressEntity = AddressFactory.createAddress(addressDTO);

        // Abspeicherung des Entity in die DB
        this.repository.save( addressEntity );
    }

    public List<AddressDTO> readAllAddress() {
        return repository.findAllAddress();
    }
}

package org.hbrs.se2.project.softwaree.control.factories;

import org.hbrs.se2.project.softwaree.dtos.AddressDTO;
import org.hbrs.se2.project.softwaree.entities.Address;

public class AddressFactory {
    private AddressFactory(){}
    public static Address createAddress(AddressDTO addressDTO){
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setCity(addressDTO.getCity());
        address.setNumber(addressDTO.getNumber());

        address.setPostalCode(addressDTO.getPostalCode());
        address.setStreet(addressDTO.getStreet());
        return address;
    }
}

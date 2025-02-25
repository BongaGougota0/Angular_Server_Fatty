package za.co.burgerfatty.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.co.burgerfatty.dto.AddressDto;
import za.co.burgerfatty.exception.AddressNotFound;
import za.co.burgerfatty.models.Address;
import za.co.burgerfatty.repositories.AddressRepository;

@Service
@AllArgsConstructor
public class AddressService {
    private static final String ADDRESS_NOT_FOUND = "Address with id %s not found";

    private final AddressRepository addressRepository;

    public boolean addAddress(Address address) {
        addressRepository.save(address);
        return true;
    }

    public AddressDto getAddress(Long addressId) {
        Address address = addressRepository.findAddressByAddressId(addressId);
        return createAddressDto(new AddressDto(), address);
    }

    public AddressDto updateAddress(AddressDto addressDto){
        Address address = addressIsPresent(addressDto);
        return createAddressDto(new AddressDto(), address);
    }

    private Address addressIsPresent(AddressDto addressDto) {
        Address address = addressRepository.findAddressByAddressId(addressDto.getAddressId());
        if(address.getAddressId() == null){
            throw new AddressNotFound(String.format(ADDRESS_NOT_FOUND, addressDto.getAddressId()));
        }
        return address;
    }

    private AddressDto createAddressDto(AddressDto addressDto, Address address) {
        addressDto.setCity(address.getCity());
        addressDto.setZip(address.getZip());
        addressDto.setProvince(address.getProvince());
        addressDto.setStreetName(address.getStreetName());
        return addressDto;
    }

}

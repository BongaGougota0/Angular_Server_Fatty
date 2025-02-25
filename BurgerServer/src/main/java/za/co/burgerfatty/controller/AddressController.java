package za.co.burgerfatty.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.burgerfatty.dto.AddressDto;
import za.co.burgerfatty.models.Address;
import za.co.burgerfatty.service.AddressService;
import za.co.burgerfatty.service.BurgerUsersService;

@RestController
@RequestMapping(path = "api/address", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressController {
    private final AddressService addressService;
    private final BurgerUsersService burgerUsersService;

    public AddressController(AddressService addressService, BurgerUsersService burgerUsersService) {
        this.addressService = addressService;
        this.burgerUsersService = burgerUsersService;
    }

    @GetMapping("")
    public ResponseEntity<AddressDto> getUserAddress(@RequestParam("email") String userEmail) {
        long addressId = getAddressIdByUserEmail(userEmail);
        return ResponseEntity.ok().body(addressService.getAddress(addressId));
    }

    @PostMapping("/post")
    public ResponseEntity<AddressDto> postUserAddress(@RequestBody AddressDto addressDto) {
        Address address = addressService.addAddress(addressDto);
        if(address.getAddressId() != null) {
            return ResponseEntity.ok().body(addressDto);
        }else {
            return ResponseEntity.badRequest().body(addressDto);
        }
    }

    private long getAddressIdByUserEmail(String userEmail) {
        return burgerUsersService.getUserByEmail(userEmail).getAddress().getAddressId();
    }
}

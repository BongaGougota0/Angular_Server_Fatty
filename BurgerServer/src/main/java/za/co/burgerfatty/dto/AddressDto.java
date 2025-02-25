package za.co.burgerfatty.dto;

import lombok.Data;

@Data
public class AddressDto {
    private Long addressId;
    private String streetName;
    private String city;
    private String province;
    private String zip;
    private String userEmail;
}

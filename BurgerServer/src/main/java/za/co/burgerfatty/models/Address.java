package za.co.burgerfatty.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table
@Entity(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;
    @Column(name = "street_name")
    private String streetName;
    @Column(name = "city")
    private String city;
    @Column(name = "province")
    private String province;
    @Column(name = "zip_code")
    private String zip;
}


package za.co.burgerfatty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.burgerfatty.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findAddressByAddressId(Long addressId);
}

package address;

import org.springframework.data.jpa.repository.JpaRepository;

interface AddressRepository extends JpaRepository<Address, Long> {

}
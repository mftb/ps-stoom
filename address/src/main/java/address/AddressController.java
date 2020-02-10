package address;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class AddressController {

    private final AddressRepository repository;

    AddressController(AddressRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("/Address")
    List<Address> all() {
        return repository.findAll();
    }

    @PostMapping("/Address")
    Address newAddress(@RequestBody Address newAddress) {
        return repository.save(newAddress);
    }

    // Single item

    @GetMapping("/Address/{id}")
    Address one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));
    }

    @PutMapping("/Address/{id}")
    Address replaceAddress(@RequestBody Address newAddress, @PathVariable Long id) {

        return repository.findById(id)
                .map(Address -> {
                    Address.setCity(newAddress.getCity());
                    Address.setComplement(newAddress.getComplement());
                    Address.setCountry(newAddress.getCountry());
                    Address.setLatitude(newAddress.getLatitude());
                    Address.setLongitude(newAddress.getLongitude());
                    Address.setNeighbourhood(newAddress.getNeighbourhood());
                    Address.setNumber(newAddress.getNumber());
                    Address.setState(newAddress.getState());
                    Address.setStreetName(newAddress.getStreetName());
                    Address.setZipcode(newAddress.getZipcode());
                    return repository.save(Address);
                })
                .orElseGet(() -> {
                    newAddress.setId(id);
                    return repository.save(newAddress);
                });
    }

    @DeleteMapping("/Address/{id}")
    void deleteAddress(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
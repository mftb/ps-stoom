package address;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import com.google.maps.*;
import com.google.maps.model.GeocodingResult;

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
        if (newAddress.getLatitude() == null || newAddress.getLongitude() == null ) {
            // retrieve latitude and longitude from google geocoding
            String key = "AIzaSyBm3xh9oZP1ksMWcMzVaZQevWlrtb8tIgc";
            String readable_addres =  newAddress.getNumber().toString() + " " + newAddress.getStreetName() +
                    " " + newAddress.getCity() + " " + newAddress.getState();

            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(key)
                    .build();
            try {
                GeocodingResult[] results =  GeocodingApi.geocode(context,
                        readable_addres).await();

                if (results.length > 0) {
                    Double lat = results[0].geometry.location.lat;
                    Double lng = results[0].geometry.location.lng;
                    newAddress.setLatitude(lat.toString());
                    newAddress.setLongitude(lng.toString());
                }
            } catch (Exception exception) {
                newAddress.setLatitude("");
                newAddress.setLongitude("");
            }

        }
        try {
            return repository.save(newAddress);
        } catch (Exception exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bad Data");
        }
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
                    try {
                        return repository.save(Address);
                    } catch (Exception exception) {
                        throw new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "Bad Data");
                    }
                })
                .orElseGet(() -> {
                    try {
                        newAddress.setId(id);
                        return repository.save(newAddress);
                    } catch (Exception exception) {
                        throw new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "Bad Data");
                    }
                });

    }

    @DeleteMapping("/Address/{id}")
    void deleteAddress(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
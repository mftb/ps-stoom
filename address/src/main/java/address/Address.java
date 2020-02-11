package address;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Address {

    private @Id @GeneratedValue Long id;

    @NotNull(message = "Street Name may not be null")
    private String streetName;
    @NotNull(message = "Number may not be null")
    private Integer number;
    private String complement; // optional
    @NotNull(message = "Nieghbourhood may not be null")
    private String neighbourhood;
    @NotNull(message = "City may not be null")
    private String city;
    @NotNull(message = "State may not be null")
    private String state;
    @NotNull(message = "Country may not be null")
    private String country;
    @NotNull(message = "Zipcode may not be null")
    private String zipcode;
    private String latitude; // optional
    private String longitude; // optional

    Address() {
    }

    // TODO: refactor construcotr to use builder pattern or a factory method in order to handle with null latitude and longitude and call the google maps api
    Address(String streetName, Integer number, String complement, String neighbourhood, String city, String state, String country, String zipcode, String latitude, String longitude) {
        this.streetName = streetName;
        this.number = number;
        this.complement = complement;
        this.neighbourhood = neighbourhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
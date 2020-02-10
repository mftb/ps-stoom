package address;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Address {

    private @Id @GeneratedValue Long id;
    @NotNull
    private String streetName;
    @NotNull
    private Integer number;
    private String complement; // optional
    @NotNull
    private String neighbourhood;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String country;
    @NotNull
    private String zipcode;
    private String latitude; // optional
    private String longitude; // optional

    Address() {
    }

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
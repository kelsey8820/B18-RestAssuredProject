package pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Locations {
    // This is instructing jackson to map the json field called location_id
    // to this particular POJO field locationId
    @JsonProperty("location_id")
    private int locationId;

    private String street_address ;
    private String postal_code ;
    private String city ;
    private String state_province ;
    private String country_id ;

    @Override
    public String toString() {
        return "Locations{" +
                "location_id=" + locationId +
                ", street_address='" + street_address + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", city='" + city + '\'' +
                ", state_province='" + state_province + '\'' +
                ", country_id=" + country_id +
                '}';
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState_province() {
        return state_province;
    }

    public void setState_province(String state_province) {
        this.state_province = state_province;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }
}

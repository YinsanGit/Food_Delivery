package org.example.ytlearning.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.example.ytlearning.emunration.VehicleType;

@Data
public class DeliveryPartnerRequest {


    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("user_name")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("dob")
    private String dob;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String email;

    @JsonProperty("address")
    private String address;

    @JsonProperty("vehicle")
    private String vehicle;

    @JsonProperty("available")
    private boolean available;


}

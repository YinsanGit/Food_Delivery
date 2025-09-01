package org.example.ytlearning.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
public class RestaurantRequest {

    private String code;
    private String name;
    private String category;
    private String description;
    private double rating;
    private String address;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("logo_url")
    private String logoUrl;
    @JsonProperty("open_time")
    private String openTime;
    @JsonProperty("close_time")
    private String closeTime;
}

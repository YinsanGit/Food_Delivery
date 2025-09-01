package org.example.ytlearning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RestaurantResponse {


    private Long id;
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
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_by")
    private String updatedBy;
}

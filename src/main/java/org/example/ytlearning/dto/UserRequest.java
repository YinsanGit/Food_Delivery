package org.example.ytlearning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder

public class UserRequest {
    private Long id;
    private String username;
    @JsonProperty("first_name")
    private String firstname;
    @JsonProperty("last_name")
    private String lastname;
    private String gender;
    @JsonProperty("dob")
    private Date dateOfBirth;
    private String email;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String address;
    @JsonProperty("user_type")
    private String UserType;
    private String status;
    @JsonProperty("device_info")
    private DeviceRequest deviceRequest;

    public static class DeviceRequest{
        @JsonProperty("device_id")
        private String deviceId;
        @JsonProperty("device_type")
        private String deviceType;
        @JsonProperty("device_model")
        private String deviceModel;
        @JsonProperty("os_version")
        private String osVersion;
        @JsonProperty("app_version")
        private String appVersion;
        @JsonProperty("last_login")
        private Date lastLogin;
        @JsonProperty("trust_device")
        private boolean trustDevice;
        private String status;
    }
}



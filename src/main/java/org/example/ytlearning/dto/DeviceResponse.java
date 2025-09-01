package org.example.ytlearning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Builder
@Data

    public class DeviceResponse {

        private Long id;
        @JsonProperty("device_id")
        @NotBlank(message = "Device ID is required")
        private String deviceId;

        @JsonProperty("device_type")
        @NotBlank(message = "Device type is required")
        private String deviceType;

        @JsonProperty("device_model")
        private String deviceModel; // optional

        @JsonProperty("os_version")
        private String osVersion;   // optional

        @JsonProperty("app_version")
        private String appVersion;  // optional

        @JsonProperty("last_login")
        private Date lastLogin;

        @JsonProperty("trust_device")
        private boolean trustDevice;


        private String status;


}

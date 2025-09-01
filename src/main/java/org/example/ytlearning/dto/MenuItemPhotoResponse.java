package org.example.ytlearning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemPhotoResponse {


    private Long id;
    @JsonProperty("file_type")
    private String fileType;
    @JsonProperty("file_format")
    private String fileFormat;
    @JsonProperty("file_size")
    private double fileSize;
    @JsonProperty("file_name")
    private String fileName;
    @JsonProperty("small_url")
    private String smallUrl;
    @JsonProperty("medium_url")
    private String mediumUrl;
    @JsonProperty("large_url")
    private String largeUrl;
    @JsonProperty("uploaded_by")
    private String uploadedBy;
    private String status;

}

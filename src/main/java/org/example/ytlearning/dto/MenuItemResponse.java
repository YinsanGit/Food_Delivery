package org.example.ytlearning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemResponse {
    private Long id;
    private String code;
    private String name;
    private String description;
    private double price;
    private Integer availability;
    @JsonProperty("restaurant_id")
    private Long restaurantId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_by")
    private String updatedBy;

//    @JsonProperty("menu_item_photo")
//    private List<MenuItemPhotoRequest> menuItemPhotoRequests;
}

package org.example.ytlearning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class MenuItemRequest {
    private String code;
    private String name;
    private String description;
    private double price;
    private Integer availability;
    @JsonProperty("restaurant_id")
    private Long restaurantId;

    @JsonProperty("menu_item_photo")
    private List<MenuItemPhotoRequest> menuItemPhotoRequests;
}

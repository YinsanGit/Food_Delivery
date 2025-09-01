package org.example.ytlearning.dto;

import lombok.Data;

@Data
public class OrderMenuItemRequest {
    private String code;
    private String name;
    private String description;
    private double price;
}

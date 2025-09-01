package org.example.ytlearning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class OrderStatusRequest {
    private Long Id;

    @JsonProperty("order_id")
    private String OrderId;

    @JsonProperty("order_status")
    private String OrderStatus;
}

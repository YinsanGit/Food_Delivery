package org.example.ytlearning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    @JsonProperty("order_id")
    private String orderId;
    @JsonProperty("total_amount")
    private double totalAmount;
    @JsonProperty("payment_method")
    private String paymentMethod;
    @JsonProperty("order_status")
    private String orderStatus;
}

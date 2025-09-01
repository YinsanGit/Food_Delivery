package org.example.ytlearning.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class PaymentRequest {
    @JsonProperty("payment_method")
    private String paymentMethod;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("payment_description")
    private String paymentDescription;
}

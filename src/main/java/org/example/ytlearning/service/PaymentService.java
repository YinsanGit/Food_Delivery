package org.example.ytlearning.service;

import org.example.ytlearning.dto.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    String pay(PaymentRequest paymentRequest);

    String inquiry(String orderId);

}

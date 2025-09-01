package org.example.ytlearning.service.impl;

import org.example.ytlearning.dto.PaymentRequest;
import org.example.ytlearning.service.PaymentService;
import org.example.ytlearning.service.handler.PaymentHandlerService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceimpl implements PaymentService {
    private final PaymentHandlerService paymentHandlerService;

    public PaymentServiceimpl(PaymentHandlerService paymentHandlerService) {
        this.paymentHandlerService = paymentHandlerService;
    }

    @Override
    public String pay(PaymentRequest paymentRequest) {
        return paymentHandlerService.postingToPaymentGateway(paymentRequest);

    }

    @Override
    public String inquiry(String orderId) {
        return "";
    }
}

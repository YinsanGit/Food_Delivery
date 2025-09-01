package org.example.ytlearning.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.ytlearning.dto.PaymentRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class KHQRHandlerService {

    private final RestTemplate restTemplate;

    public KHQRHandlerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String postingToKhApi(PaymentRequest paymentRequest) {
        try {


            final String url = "http://localhost:8080/kh/api/v1/kh/payment/transactionTesting";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + "my-secret-token");

            HttpEntity<PaymentRequest> httpEntity = new HttpEntity<>(paymentRequest, headers);
            log.info("Posting to KH API: {}", paymentRequest);
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            log.info("Response from KH API: {}", response.getBody());

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        } catch (Exception ex) {
            log.error("KHQR API service response error: {}", ex.getMessage());
            return null;
        }

        return null;
    }

}

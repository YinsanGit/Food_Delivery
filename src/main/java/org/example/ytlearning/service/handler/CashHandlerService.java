package org.example.ytlearning.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.ytlearning.dto.PaymentRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CashHandlerService {

    private final RestTemplate restTemplate;

    public CashHandlerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String postingToCashApi(PaymentRequest paymentRequest) {
        try {


            final String url = "http://localhost:8080/kh/api/v1/cash/Testing";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + "my-secret-token-cash");

            HttpEntity<PaymentRequest> httpEntity = new HttpEntity<>(paymentRequest, headers);
            log.info("Posting to Cash API: {}", paymentRequest);
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            log.info("Response from Cash API: {}", response.getBody());

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        } catch (Exception ex) {
            log.error("Cash API service response error: {}", ex.getMessage());

        }
        return null;
    }
}

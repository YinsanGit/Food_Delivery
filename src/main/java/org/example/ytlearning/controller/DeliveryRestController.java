package org.example.ytlearning.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ytlearning.dto.DeliveryPartnerRequest;
import org.example.ytlearning.dto.DeliveryPartnerResponse;
import org.example.ytlearning.dto.UserResponse;
import org.example.ytlearning.exception.ApiResponseUtil;
import org.example.ytlearning.service.DeliveryPartnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DeliveryRestController {
    private final DeliveryPartnerService deliveryPartnerService;

    @PostMapping(value = "v1/delivery-partner", consumes = "application/json", produces = "application/json")
    private ResponseEntity<Object> create(@Valid @RequestBody DeliveryPartnerRequest deliveryPartnerRequest) {

        log.info("Create Delivery Partner with Request: {}", deliveryPartnerRequest);

        return new ResponseEntity<>(ApiResponseUtil.successResponse(deliveryPartnerService.create(deliveryPartnerRequest)), HttpStatus.OK);
    }

    @PutMapping(value = "v1/delivery-partner/{id}", consumes = "application/json", produces = "application/json")
    private ResponseEntity<Object> update(@Valid @RequestBody DeliveryPartnerRequest deliveryPartnerRequest,
                                                @PathVariable Long id) {
        log.info("Update delivery Partner with Request: {}", deliveryPartnerRequest);
        return new ResponseEntity<>(ApiResponseUtil.successResponse(deliveryPartnerService.update(id, deliveryPartnerRequest)), HttpStatus.OK);
    }

    @DeleteMapping(value = "v1/delivery-partner/{id}", produces = "application/json")
    private ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Delete delivery Partner with ID: {}", id);
        deliveryPartnerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "v1/delivery-partner/{id}", produces = "application/json")
    private ResponseEntity<Object> findById(@PathVariable Long id) {
        log.info("Find delivery Partner by ID: {}", id);
        return new ResponseEntity<>(deliveryPartnerService.findById(id),HttpStatus.OK);
    }

    @GetMapping(value = "v1/delivery-partner", produces = "application/json")
    private ResponseEntity<List<DeliveryPartnerResponse>> findAll() {
        log.info("Find all users");
        return new ResponseEntity<>(deliveryPartnerService.getAll(),HttpStatus.OK);

    }


}

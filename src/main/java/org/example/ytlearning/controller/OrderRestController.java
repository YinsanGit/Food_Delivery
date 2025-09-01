package org.example.ytlearning.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ytlearning.dto.OrderRequest;
import org.example.ytlearning.dto.OrderStatusRequest;
import org.example.ytlearning.exception.ApiResponseUtil;
import org.example.ytlearning.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderRestController {
    private final OrderService orderService;
    @PostMapping(value = "v1/orders", consumes = "application/json", produces = "application/json")
    private ResponseEntity<Object> create( @RequestBody OrderRequest orderRequest) {

        log.info("Create Order with Request: {}", orderRequest);
        return new ResponseEntity<>(ApiResponseUtil.successResponse(orderService.create(orderRequest)), HttpStatus.OK);
    }

    @PutMapping(value = "v1/orders", consumes = "application/json", produces = "application/json")
    private ResponseEntity<Object> create( @RequestBody OrderStatusRequest orderStatusRequest) {

        log.info(" Update OrderStatus Request  {}", orderStatusRequest);
        return new ResponseEntity<>(ApiResponseUtil.successResponse(orderService.update(orderStatusRequest)), HttpStatus.OK);
    }

}

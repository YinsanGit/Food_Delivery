package org.example.ytlearning.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ytlearning.dto.*;
import org.example.ytlearning.exception.ApiResponseUtil;
import org.example.ytlearning.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RestaurantRestController {
    private final RestaurantService restaurantService;

    @PostMapping(value = "v1/restaurant", consumes = "application/json", produces = "application/json")
    private ResponseEntity<Object> create(@Valid @RequestBody RestaurantRequest restaurantRequest) {

        log.info("Create Restaurant with Request: {}", restaurantRequest);
        return new ResponseEntity<>(ApiResponseUtil.successResponse(restaurantService.create(restaurantRequest)), HttpStatus.OK);
    }

    @PutMapping(value = "v1/restaurant/{id}", consumes = "application/json", produces = "application/json")
    private ResponseEntity<Object> update(@Valid @RequestBody RestaurantRequest restaurantRequest,
                                                      @PathVariable Long id) {
        log.info("Update Restaurant with Request: {}", restaurantRequest);
        restaurantService.update(id, restaurantRequest);
        return new ResponseEntity<>(ApiResponseUtil.successResponse(restaurantService.update(id, restaurantRequest)), HttpStatus.OK);
    }

    @DeleteMapping(value = "v1/restaurant/{id}", produces = "application/json")
    private ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Delete Restaurant with ID: {}", id);
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "v1/restaurant/{id}", produces = "application/json")
    private ResponseEntity<RestaurantResponse> findById(@PathVariable Long id) {
        log.info("Find Restaurant by ID: {}", id);
        return new ResponseEntity<>(restaurantService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "v1/restaurant", produces = "application/json")
    private ResponseEntity<List<RestaurantResponse>> findAll() {
        log.info("Find all restaurant");
        return new ResponseEntity<>(restaurantService.getAll(), HttpStatus.OK);

    }


}

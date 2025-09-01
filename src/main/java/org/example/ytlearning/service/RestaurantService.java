package org.example.ytlearning.service;

import org.example.ytlearning.dto.RestaurantRequest;
import org.example.ytlearning.dto.RestaurantResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {

    RestaurantResponse create(RestaurantRequest restaurantRequest);

    RestaurantResponse update(Long id, RestaurantRequest restaurantRequest);

    void delete(Long id);

    RestaurantResponse getById(Long id);

    List<RestaurantResponse> getAll();



}

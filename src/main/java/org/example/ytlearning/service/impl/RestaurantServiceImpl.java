package org.example.ytlearning.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ytlearning.Constant.Constant;
import org.example.ytlearning.dto.RestaurantRequest;
import org.example.ytlearning.dto.RestaurantResponse;
import org.example.ytlearning.model.Restaurant;
import org.example.ytlearning.repository.RestaurantRepository;
import org.example.ytlearning.service.RestaurantService;
import org.example.ytlearning.service.handler.RestaurantHandlerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantHandlerService restaurantHandlerService;

    @Override
    public RestaurantResponse create(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = new Restaurant();
        restaurant = restaurantHandlerService
                .convertRestaurantRequestToRestaurant(restaurant, restaurantRequest);
        log.info("Save restaurant record: {}", restaurant);
        restaurantRepository.saveAndFlush(restaurant);

        return restaurantHandlerService.convertRestaurantResponseToRestaurant(restaurant);

    }

    @Override
    public RestaurantResponse update(Long id, RestaurantRequest restaurantRequest) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            Restaurant updateRestaurant = restaurantHandlerService
                    .convertRestaurantRequestToRestaurant(restaurant.get(), restaurantRequest);
            updateRestaurant.setUpdateBy(Constant.SYSTEM);
            updateRestaurant.setUpdateAt(new Date());

            restaurantRepository.saveAndFlush(updateRestaurant);
            return restaurantHandlerService.convertRestaurantResponseToRestaurant(updateRestaurant);
        }

        return new RestaurantResponse();
    }

    @Override
    public void delete(Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            restaurantRepository.deleteById(id);
        }

    }

    @Override
    public RestaurantResponse getById(Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if(restaurant.isEmpty()){
            log.info("Restaurant with ID {} not found in database", id);
            return new RestaurantResponse();
        }

        return restaurantHandlerService.convertRestaurantResponseToRestaurant(restaurant.get());
    }


    @Override
    public List<RestaurantResponse> getAll() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        if(restaurants.isEmpty()){
            log.info("Fetch all Restaurant not found in DB.");
            return List.of();
        }
        List<RestaurantResponse> restaurantResponses = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            RestaurantResponse restaurantResponse =  restaurantHandlerService
                    .convertRestaurantResponseToRestaurant(restaurant);
            restaurantResponses.add(restaurantResponse);
        }
        return restaurantResponses;

    }
}

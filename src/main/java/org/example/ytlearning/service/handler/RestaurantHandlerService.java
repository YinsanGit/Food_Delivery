package org.example.ytlearning.service.handler;

import org.example.ytlearning.Constant.Constant;
import org.example.ytlearning.dto.RestaurantRequest;
import org.example.ytlearning.dto.RestaurantResponse;
import org.example.ytlearning.model.Restaurant;
import org.example.ytlearning.utils.DateTimeUtils;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;

@Service
public class RestaurantHandlerService {
    public Restaurant convertRestaurantRequestToRestaurant(Restaurant restaurant, RestaurantRequest restaurantRequest) {

        restaurant.setCode(restaurantRequest.getCode());
        restaurant.setName(restaurantRequest.getName());
        restaurant.setCategory(restaurantRequest.getCategory());
        restaurant.setDescription(restaurantRequest.getDescription());
        restaurant.setRating(restaurantRequest.getRating());
        restaurant.setAddress(restaurantRequest.getAddress());
        restaurant.setPhoneNumber(restaurantRequest.getPhoneNumber());
        restaurant.setLogoUrl(restaurantRequest.getLogoUrl());
        restaurant.setOpenTime(DateTimeUtils.convertStringTimeToLocalTime(restaurantRequest.getOpenTime()));
        restaurant.setCloseTime(DateTimeUtils.convertStringTimeToLocalTime(restaurantRequest.getCloseTime()));
        if (restaurant.getId() == null){
            restaurant.setCreateAt(new Date());
            restaurant.setUpdateBy(Constant.SYSTEM);
        }
        return restaurant;
    }

    public RestaurantResponse convertRestaurantResponseToRestaurant(Restaurant restaurant) {
        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .code(restaurant.getCode())
                .category(restaurant.getCategory())
                .description(restaurant.getDescription())
                .rating(restaurant.getRating())
                .address(restaurant.getAddress())
                .phoneNumber(restaurant.getPhoneNumber())
                .logoUrl(restaurant.getLogoUrl())
                .openTime(restaurant.getOpenTime().toString())
                .closeTime(restaurant.getCloseTime().toString()).build();
    }
}

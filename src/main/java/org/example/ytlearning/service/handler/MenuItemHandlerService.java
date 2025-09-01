package org.example.ytlearning.service.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ytlearning.Constant.Constant;
import org.example.ytlearning.dto.MenuItemPhotoRequest;
import org.example.ytlearning.dto.MenuItemRequest;
import org.example.ytlearning.dto.MenuItemResponse;
import org.example.ytlearning.model.MenuItem;
import org.example.ytlearning.model.MenuItemPhoto;
import org.example.ytlearning.model.Restaurant;
import org.example.ytlearning.repository.MenuItemPhotoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuItemHandlerService {

    final MenuItemPhotoRepository menuItemPhotoRepository;

    public boolean verifyMenuItemPhoto(MenuItemRequest menuItemRequest) {
        // verify menu item photo
        // logic verify menu item photo will write here
        Set<Long> menuItemPhotoIds = menuItemRequest
                .getMenuItemPhotoRequests()
                .stream().map(MenuItemPhotoRequest::getId).
                collect(Collectors.toSet());

        List<MenuItemPhoto> menuItemPhotos = menuItemPhotoRepository.findAllByIdIn(menuItemPhotoIds);
        if(menuItemPhotos.isEmpty()) {
            log.error("Menu item photo not found" + menuItemPhotos);
            return false;
        }
        return true;
    }

    public MenuItem convertMenuItemRequestToMenuItem(MenuItem menuItem
            ,Restaurant restaurant, MenuItemRequest MenuItemRequest) {

        menuItem.setCode(MenuItemRequest.getCode());
        menuItem.setName(MenuItemRequest.getName());
        menuItem.setDescription(MenuItemRequest.getDescription());
        menuItem.setPrice(MenuItemRequest.getPrice());
        menuItem.setAvailability(MenuItemRequest.getAvailability());
        menuItem.setRestaurant(restaurant);
        if (menuItem.getId() == null){
            menuItem.setCreateAt(new Date());
            menuItem.setCreateBy(Constant.SYSTEM);
        }

        return menuItem;
    }

    public MenuItemResponse convertMenuItemToMenuItemResponse(MenuItem menuItem) {
        return MenuItemResponse.builder()
                .id(menuItem.getId())
                .code(menuItem.getCode())
                .name(menuItem.getName())
                .description(menuItem.getDescription())
                .price(menuItem.getPrice())
                .availability(menuItem.getAvailability())
                .restaurantId(menuItem.getRestaurant().getId())
                .build();
    }
}

package org.example.ytlearning.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ytlearning.Constant.Constant;
import org.example.ytlearning.dto.MenuItemRequest;
import org.example.ytlearning.dto.MenuItemResponse;
import org.example.ytlearning.model.MenuItem;
import org.example.ytlearning.model.Restaurant;
import org.example.ytlearning.repository.MenuItemRepository;
import org.example.ytlearning.repository.RestaurantRepository;
import org.example.ytlearning.service.MenuItemService;
import org.example.ytlearning.service.handler.MenuItemHandlerService;
import org.example.ytlearning.service.handler.MenuItemPhotoHandlerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Slf4j
@AllArgsConstructor
@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemHandlerService menuItemHandlerService;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemPhotoHandlerService menuItemPhotoHandlerService;

    @Override
    public MenuItemResponse create(MenuItemRequest MenuItemRequest) {


        if(!menuItemHandlerService.verifyMenuItemPhoto(MenuItemRequest)) {
            return new MenuItemResponse();
        }

        Optional<Restaurant> restaurant = restaurantRepository
                .findById(MenuItemRequest.getRestaurantId());

        if (restaurant.isEmpty()) {
            log.error("Restaurant with ID {} not found in database", MenuItemRequest.getRestaurantId());
            return new MenuItemResponse();
        }

        MenuItem menuItem = new MenuItem();
        menuItem = menuItemHandlerService.convertMenuItemRequestToMenuItem(menuItem, restaurant.get(), MenuItemRequest);
        log.info("Create menu item record: {}", menuItem);
        menuItemRepository.saveAndFlush(menuItem);

        menuItemPhotoHandlerService.updateFileByMenuItemAndFileId(menuItem, MenuItemRequest.getMenuItemPhotoRequests());

        return menuItemHandlerService.convertMenuItemToMenuItemResponse(menuItem);
    }

    @Override
    public MenuItemResponse update(Long id, MenuItemRequest MenuItemRequest) {

        Optional<Restaurant> restaurant = restaurantRepository
                .findById(MenuItemRequest.getRestaurantId());

        if (restaurant.isEmpty()) {
            log.error("Restaurant with update by ID {} not found in database", MenuItemRequest.getRestaurantId());
            return new MenuItemResponse();
        }

        Optional<MenuItem> menuItem = menuItemRepository.findById(id);

        if (menuItem.isEmpty()) {
            log.error("Menu item with ID {} not found in database", id);
            return new MenuItemResponse();
        }

        MenuItem updateMenuItem = menuItemHandlerService
                .convertMenuItemRequestToMenuItem(menuItem.get(), restaurant.get(), MenuItemRequest);
        updateMenuItem.setUpdateAt(new Date());
        updateMenuItem.setUpdateBy(Constant.SYSTEM);
        log.info("Update menu item record: {}", updateMenuItem);
        menuItemRepository.saveAndFlush(updateMenuItem);

        return menuItemHandlerService.convertMenuItemToMenuItemResponse(updateMenuItem);
    }

    @Override
    public void delete(Long id) {
        menuItemRepository.deleteById(id);
    }

    @Override
    public MenuItemResponse getById(Long id) {
        Optional<MenuItem> menuItem = menuItemRepository.findById(id);
        return menuItem.map(menuItemHandlerService::convertMenuItemToMenuItemResponse).orElse(new MenuItemResponse());
    }

    @Override
    public List<MenuItemResponse> getAll() {
        List<MenuItem> menuItems = menuItemRepository.findAll();
        if (menuItems.isEmpty()) {
            return List.of();
        }

        List<MenuItemResponse> menuItemResponses = new ArrayList<>();
        for (MenuItem menuItem : menuItems) {
            var menuItemResponse = menuItemHandlerService.convertMenuItemToMenuItemResponse(menuItem);
            menuItemResponses.add(menuItemResponse);
        }
        return menuItemResponses;
    }
}


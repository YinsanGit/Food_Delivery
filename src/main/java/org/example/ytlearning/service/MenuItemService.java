package org.example.ytlearning.service;

import org.example.ytlearning.dto.MenuItemRequest;
import org.example.ytlearning.dto.MenuItemResponse;

import java.util.List;

public interface MenuItemService {

    MenuItemResponse create(MenuItemRequest MenuItemRequest);

    MenuItemResponse update(Long id, MenuItemRequest MenuItemRequest);

    void delete(Long id);

    MenuItemResponse getById(Long id);

    List<MenuItemResponse> getAll();




}

package org.example.ytlearning.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ytlearning.dto.MenuItemRequest;
import org.example.ytlearning.dto.UserResponse;
import org.example.ytlearning.service.MenuItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
public class MenuItemRestController {
    private final MenuItemService menuItemService;

    @PostMapping(value = "v1/menu-item", consumes = "application/json", produces = "application/json")
    private ResponseEntity<Object> create(@Valid @RequestBody MenuItemRequest menuItemRequest){

        log.info("Create MenuItem with Request: {}", menuItemRequest);
        return new ResponseEntity<>(menuItemService.create(menuItemRequest), HttpStatus.OK);
    }

    @PutMapping(value = "v1/menu-item/{id}", consumes = "application/json", produces = "application/json")
    private ResponseEntity<Object> update(@Valid @RequestBody MenuItemRequest menuItemRequest,
                                                    @PathVariable Long id) {
        log.info("Update menu-item with Request: {}", menuItemRequest);
        return new ResponseEntity<>(menuItemService.update(id, menuItemRequest), HttpStatus.OK);
    }

    @DeleteMapping(value = "v1/menu-item/{id}", produces = "application/json")
    private ResponseEntity<UserResponse> delete(@PathVariable Long id) {
        log.info("Intercept request delete menu-item v1 with id: {}", id);
        menuItemService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "v1/menu-item/{id}", produces = "application/json")
    private ResponseEntity<Object> findById(@PathVariable Long id) {
        log.info("Find Menu-item by ID: {}", id);
        return new ResponseEntity<>(menuItemService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "v1/menu-item", produces = "application/json")
    private ResponseEntity<Object> findAll() {
        log.info("Intercept request find all menu-item v1");
        return new ResponseEntity<>(menuItemService.getAll(), HttpStatus.OK);
    }
    

}

package org.example.ytlearning.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ytlearning.dto.MenuItemPhotoRequest;
import org.example.ytlearning.dto.MenuItemPhotoResponse;
import org.example.ytlearning.dto.MenuItemRequest;
import org.example.ytlearning.dto.MenuItemResponse;
import org.example.ytlearning.exception.ApiResponseUtil;
import org.example.ytlearning.service.MenuItemPhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MenuItemPhotoRestController {
    private final MenuItemPhotoService menuItemPhotoService;

    @PostMapping(value = "v1/menu-item/photo/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE
            , produces = "application/json")

    private ResponseEntity<Object> upload(@RequestParam("files")MultipartFile[] files
            ,  MenuItemPhotoRequest menuItemPhotoRequest) {

            log.info("Create menu-item-photo with Request: {}", menuItemPhotoRequest);

        return new ResponseEntity<>(ApiResponseUtil.successResponse(menuItemPhotoService.upload(files, menuItemPhotoRequest)), HttpStatus.OK);


    }

    @PutMapping(value = "v1/menu-item/photo/{id}", consumes = "application/json", produces = "application/json")
    private ResponseEntity<Object> update(@Valid @RequestBody MenuItemPhotoRequest menuItemRequest,
                                                         @PathVariable Long id) {
        log.info("Update menu-item Photo with Request: {}", menuItemRequest);

        return new ResponseEntity<>(menuItemPhotoService.update(id,menuItemRequest), HttpStatus.OK);
    }

    @DeleteMapping(value = "v1/menu-item/photo/{id}", produces = "application/json")
    private ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Delete Menu-item Photo with ID: {}", id);
        menuItemPhotoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "v1/menu-item/photo/{id}", produces = "application/json")
    private ResponseEntity<Object> findById(@PathVariable Long id) {
        log.info("Find Menu-item Photo by ID: {}", id);
        return new ResponseEntity<>(menuItemPhotoService.getById(id),HttpStatus.OK);
    }

    @GetMapping(value = "v1/menu-item/photo", produces = "application/json")
    private ResponseEntity<Object> findAll() {
        log.info("Find all menu-item Photo");
        return new ResponseEntity<>(menuItemPhotoService.getAll(), HttpStatus.OK);
    }
    

}

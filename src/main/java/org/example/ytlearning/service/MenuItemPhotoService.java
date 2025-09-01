package org.example.ytlearning.service;

import org.example.ytlearning.dto.MenuItemPhotoRequest;
import org.example.ytlearning.dto.MenuItemPhotoResponse;
import org.example.ytlearning.dto.MenuItemRequest;
import org.example.ytlearning.model.MenuItemPhoto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface MenuItemPhotoService {

//    List<MenuItemPhotoResponse> upload(MultipartFile[] files, MenuItemRequest menuItemRequest);
    List<MenuItemPhotoResponse> upload(MultipartFile[] Files,MenuItemPhotoRequest menuItemPhotoRequest);

    MenuItemPhotoResponse update(Long id, MenuItemPhotoRequest menuItemPhotoRequest);

    void delete(Long id);

    MenuItemPhotoResponse getById(Long id);

    List<MenuItemPhotoResponse> getAll();

}

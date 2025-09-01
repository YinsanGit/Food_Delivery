package org.example.ytlearning.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.example.ytlearning.Constant.Constant;
import org.example.ytlearning.dto.MenuItemPhotoRequest;
import org.example.ytlearning.dto.MenuItemPhotoResponse;
import org.example.ytlearning.model.MenuItemPhoto;
import org.example.ytlearning.repository.MenuItemPhotoRepository;
import org.example.ytlearning.service.MenuItemPhotoService;
import org.example.ytlearning.service.handler.MenuItemPhotoHandlerService;
import org.example.ytlearning.utils.StringClassUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class MenuItemPhotoServiceImpl implements MenuItemPhotoService {

    private final MenuItemPhotoRepository menuItemPhotoRepository;
    private final MenuItemPhotoHandlerService menuItemPhotoHandlerService;
    private static final String FILE_UPLOAD_PATH = "C:/Users/YIN SAN/IdeaProjects/YT-Learning/upload/";



    @Override
    public List<MenuItemPhotoResponse> upload(MultipartFile[] files , MenuItemPhotoRequest menuItemPhotoRequest) {

        menuItemPhotoHandlerService.validateFileUpload(files);
        menuItemPhotoHandlerService.ValidateValidFileUpload(files);



        List<MenuItemPhotoResponse> menuItemPhotoResponses = new ArrayList<>();


        try {
            for (MultipartFile file : files) {
                var name = FilenameUtils.removeExtension(file.getOriginalFilename());
                var extensionName = StringClassUtils.getFileExtension(file.getOriginalFilename());
                var fileName = name + "." + extensionName;


                File filePathTemp = new File(FILE_UPLOAD_PATH + fileName);
                file.transferTo(filePathTemp);

                menuItemPhotoRequest.setFileFormat(extensionName);
                menuItemPhotoRequest.setFileName(name);
                menuItemPhotoRequest.setFileSize(file.getSize());
                menuItemPhotoRequest.setSmallUrl(FILE_UPLOAD_PATH + "/" + name + extensionName);
                menuItemPhotoRequest.setMediumUrl(FILE_UPLOAD_PATH + "/" + name + extensionName);
                menuItemPhotoRequest.setLargeUrl(FILE_UPLOAD_PATH + "/" + name + extensionName);

                MenuItemPhoto menuItemPhoto = new MenuItemPhoto();
                menuItemPhoto = menuItemPhotoHandlerService.convertMenuItemPhotoRequestToMenuItemPhoto(menuItemPhotoRequest, menuItemPhoto);
                log.info("Save menu item photo record: {}", menuItemPhoto);
                menuItemPhotoRepository.save(menuItemPhoto);
                menuItemPhotoResponses.add(menuItemPhotoHandlerService.convertMenuItemPhotoToMenuItemPhotoResponse(menuItemPhoto));
            }
        }catch (Exception ex){
            log.error("Upload file error: {}", ex.getMessage());
        }

        return menuItemPhotoResponses;

    }

    @Override
    public MenuItemPhotoResponse update(Long id, MenuItemPhotoRequest menuItemPhotoRequest) {
        Optional<MenuItemPhoto> menuItemPhoto = menuItemPhotoRepository.findById(id);

        if(menuItemPhoto.isEmpty()){
            log.info("Menu item photo with ID {} not found in database", id);
            return new MenuItemPhotoResponse();
        }

        MenuItemPhoto updateMenuItemPhoto = menuItemPhotoHandlerService
                .convertMenuItemPhotoRequestToMenuItemPhoto(menuItemPhotoRequest,menuItemPhoto.get());
        updateMenuItemPhoto.setUpdateAt(new Date());
        updateMenuItemPhoto.setUpdateBy(Constant.SYSTEM);
        menuItemPhotoRepository.saveAndFlush(updateMenuItemPhoto);
        return menuItemPhotoHandlerService.convertMenuItemPhotoToMenuItemPhotoResponse(updateMenuItemPhoto);
    }

    @Override
    public void delete(Long id) {
        menuItemPhotoRepository.deleteById(id);
    }

    @Override
    public MenuItemPhotoResponse getById(Long id) {
        Optional<MenuItemPhoto> menuItemPhoto = menuItemPhotoRepository.findById(id);
        return menuItemPhoto
                .map(menuItemPhotoHandlerService::convertMenuItemPhotoToMenuItemPhotoResponse)
                .orElse(null);

    }

    @Override
    public List<MenuItemPhotoResponse> getAll() {
        List<MenuItemPhoto> menuItemPhotos = menuItemPhotoRepository.findAll();
        if(menuItemPhotos.isEmpty()) {
            return List.of();
        }

        List<MenuItemPhotoResponse> menuItemPhotoResponses = new ArrayList<>();
        for(MenuItemPhoto itemPhoto : menuItemPhotos) {
            menuItemPhotoResponses.add(menuItemPhotoHandlerService.convertMenuItemPhotoToMenuItemPhotoResponse(itemPhoto));
        }
        return menuItemPhotoResponses;
    }
}

package org.example.ytlearning.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.ytlearning.Constant.Constant;
import org.example.ytlearning.dto.MenuItemPhotoRequest;
import org.example.ytlearning.dto.MenuItemPhotoResponse;
import org.example.ytlearning.dto.MenuItemResponse;
import org.example.ytlearning.model.MenuItem;
import org.example.ytlearning.model.MenuItemPhoto;
import org.example.ytlearning.repository.MenuItemPhotoRepository;
import org.example.ytlearning.utils.StringClassUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MenuItemPhotoHandlerService {
    private final MenuItemPhotoRepository menuItemPhotoRepository;
    private final List<String> FILE_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png");

    public MenuItemPhotoHandlerService(MenuItemPhotoRepository menuItemPhotoRepository) {
        this.menuItemPhotoRepository = menuItemPhotoRepository;
    }

    public void validateFileUpload(MultipartFile[] files) {
        // upload file to server
        if(files.length == 0){
            log.warn("File name is empty");
            throw new IllegalArgumentException("File name is empty");
        }
    }

    public void ValidateValidFileUpload(MultipartFile[] files) {
        for (MultipartFile file : files) {
            var fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            var extension = StringClassUtils.getFileExtension(fileName);
            if(!FILE_EXTENSIONS.contains(extension)){
                log.warn("File extension not allow to upload, please verify. File name: {}", fileName);
                throw new IllegalArgumentException("File extension not allow to upload, please verify");
            }
        }
    }




    public void updateFileByMenuItemAndFileId(MenuItem menuItem, List<MenuItemPhotoRequest>menuItemPhotoRequests)  {
        final Set<Long> menuItemPhotoIds = menuItemPhotoRequests.stream()
                .map(MenuItemPhotoRequest::getId)
                .collect(Collectors.toSet());
        List<MenuItemPhoto> menuItemPhotos = menuItemPhotoRepository.findAllByIdIn(menuItemPhotoIds);

        for (MenuItemPhoto menuItemPhoto : menuItemPhotos) {
            menuItemPhoto.setMenuItem(menuItem);
            menuItemPhotoRepository.saveAndFlush(menuItemPhoto);
        }
    }

    public MenuItemPhoto convertMenuItemPhotoRequestToMenuItemPhoto(MenuItemPhotoRequest menuItemPhotoRequest,MenuItemPhoto menuItemPhoto
            ){

            menuItemPhoto.setFileName(menuItemPhotoRequest.getFileName());
            menuItemPhoto.setFileType(menuItemPhotoRequest.getFileType());
            menuItemPhoto.setFileSize(menuItemPhotoRequest.getFileSize());
            menuItemPhoto.setFileFormat(menuItemPhotoRequest.getFileFormat());
            menuItemPhoto.setSmallUrl(menuItemPhotoRequest.getSmallUrl());
            menuItemPhoto.setMediumUrl(menuItemPhotoRequest.getMediumUrl());
            menuItemPhoto.setLargeUrl(menuItemPhotoRequest.getLargeUrl());
            menuItemPhoto.setUploadedBy(menuItemPhotoRequest.getUploadedBy());

        menuItemPhoto.setStatus(Constant.ACTIVE);
        if(StringUtils.hasText(menuItemPhotoRequest.getStatus())) {
            menuItemPhoto.setStatus(menuItemPhotoRequest.getStatus());
        }

        if(menuItemPhoto.getId() == null) {
            menuItemPhoto.setCreateAt(new Date());
            menuItemPhoto.setUpdateBy(Constant.SYSTEM);
        }
        return menuItemPhoto;
    }

    public MenuItemPhotoResponse convertMenuItemPhotoToMenuItemPhotoResponse(MenuItemPhoto menuItemPhoto) {
        return MenuItemPhotoResponse.builder()
                .id(menuItemPhoto.getId())
                .fileName(menuItemPhoto.getFileName())
                .fileType(menuItemPhoto.getFileType())
                .fileFormat(menuItemPhoto.getFileFormat())
                .fileSize(menuItemPhoto.getFileSize())
                .smallUrl(menuItemPhoto.getSmallUrl())
                .mediumUrl(menuItemPhoto.getMediumUrl())
                .largeUrl(menuItemPhoto.getLargeUrl())
                .uploadedBy(menuItemPhoto.getUploadedBy())
                .status(menuItemPhoto.getStatus())
                .build();
    }
}



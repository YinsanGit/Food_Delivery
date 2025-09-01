package org.example.ytlearning.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.ytlearning.dto.DeviceRequest;
import org.example.ytlearning.dto.DeviceResponse;
import org.example.ytlearning.dto.UserRequest;
import org.example.ytlearning.dto.UserResponse;
import org.example.ytlearning.emunration.UserType;
import org.example.ytlearning.model.Device;
import org.example.ytlearning.model.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.example.ytlearning.utils.DateTimeUtils;
import java.util.Date;

import static org.example.ytlearning.Constant.Constant.SYSTEM;
import static org.example.ytlearning.Constant.Constant.USER_STATUS_ACTIVE;

@Slf4j
@Service


public class UserHandlerService {

    public void usernameHasText(String username) {
//    Return true if username is a non-blank string, otherwise false.
      if (StringUtils.hasText(username))
      {
          return;
      }
      log.info("Username is empty");
      throw new IllegalArgumentException("Username is empty");


    }

    public void phoneNumberHasText(String phoneNumber) {
        if (StringUtils.hasText(phoneNumber)){
            return;
        }
        log.info("Phone number is empty");
        throw new IllegalArgumentException("Phone number is empty");
    }

//    public Date convertStringToLocalDate(String dateOfBirth) {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            return dateFormat.parse(dateOfBirth);
//        } catch (Exception e) {
//            log.error("Error parsing date: {}", e.getMessage());
//            throw new IllegalArgumentException("Invalid date format");
//        }
//    }


    public UserResponse convertUserToUserResponse(final User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .gender(user.getGender())
                .dateOfBirth(user.getDateOfBirth())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .address(user.getAddress())
                .userType(user.getUserType())
                .createdBy(user.getCreateBy())
//                .createdAt(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getCreateAt()))
                .createdAt(String.valueOf(user.getCreateAt()))
                .updatedBy(user.getUpdateBy())
                .updatedAt(String.valueOf(user.getUpdateAt()))
//                .updatedAt(user.getUpdateAt() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getUpdateAt()) : null)
                .build();
    }


    public User convertUserRequestToUser(final UserRequest userRequest){
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setGender(userRequest.getGender());
        user.setDateOfBirth(DateTimeUtils.convertStringToDate(String.valueOf(userRequest.getDateOfBirth())));
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setEmail(userRequest.getEmail());
        user.setAddress(userRequest.getAddress());
        user.setUserType(String.valueOf(UserType.valueOf(userRequest.getUserType())));
        user.setStatus(USER_STATUS_ACTIVE);
        user.setCreateBy(SYSTEM);
        user.setCreateAt(new Date());

        return user;
    }

    public Device convertDeviceRequestToUserDevice(final User user, final DeviceRequest deviceRequest){
        Device device = new Device();
        device.setDeviceId(deviceRequest.getDeviceId());
        device.setDeviceModel(deviceRequest.getDeviceModel());
        device.setDeviceType(deviceRequest.getDeviceType());
        device.setOsVersion(deviceRequest.getOsVersion());
        device.setAppVersion(deviceRequest.getAppVersion());
        device.setTrustDevice(deviceRequest.isTrustDevice());
        device.setUser(user);

        return device;
    }

    public DeviceResponse convertUserDeviceToDeviceResponse(final Device device) {

        return DeviceResponse.builder()
                .id(device.getId())
                .deviceId(device.getDeviceId())
                .deviceType(device.getDeviceType())
                .deviceModel(device.getDeviceModel())
                .osVersion(device.getOsVersion())
                .appVersion(device.getAppVersion())
                .trustDevice(device.isTrustDevice())
                .lastLogin(device.getLastLogin())
                .status(device.getStatus())
                .build();
    }





}

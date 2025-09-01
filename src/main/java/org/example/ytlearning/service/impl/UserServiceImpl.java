package org.example.ytlearning.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.ytlearning.dto.DeviceRequest;
import org.example.ytlearning.dto.UserRequest;
import org.example.ytlearning.dto.UserResponse;
import org.example.ytlearning.exception.UserNotFoundErrorException;
import org.example.ytlearning.model.Device;
import org.example.ytlearning.model.User;
import org.example.ytlearning.repository.DeviceRepository;
import org.example.ytlearning.repository.UserRepository;
import org.example.ytlearning.service.handler.UserHandlerService;
import org.example.ytlearning.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final UserHandlerService userHandlerService;


    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, DeviceRepository deviceRepository, UserHandlerService userHandlerService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.userHandlerService = userHandlerService;
    }

    @Transactional
    @Override
    public UserResponse create(UserRequest userRequest) {
        userHandlerService.usernameHasText(userRequest.getUsername());
        userHandlerService.phoneNumberHasText(userRequest.getPhoneNumber());

//        User user = modelMapper.map(userRequest,User.class);
        User user = userHandlerService.convertUserRequestToUser(userRequest);
        log.info("Save user record: {}", user);

        userRepository.saveAndFlush(user);

        if (user.getId() != null) {
            final DeviceRequest deviceRequest = userRequest.getDeviceRequest();

            if (deviceRequest == null) {
                throw new IllegalArgumentException("Device information is required");
            }

            if (deviceRequest.getDeviceId() == null || deviceRequest.getDeviceId().isBlank()) {
                throw new IllegalArgumentException("Device ID cannot be empty");
            }

            if (deviceRequest.getDeviceType() == null || deviceRequest.getDeviceType().isBlank()) {
                throw new IllegalArgumentException("Device type cannot be empty");
            }

            // optional: check for duplicates
            if (deviceRepository.existsByDeviceId(deviceRequest.getDeviceId())) {
                throw new IllegalStateException("Device already registered");
            }

            Device device = userHandlerService.convertDeviceRequestToUserDevice(user, deviceRequest);
            deviceRepository.saveAndFlush(device);
        }
        return userHandlerService.convertUserToUserResponse(user);
    }

    @Transactional
    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            log.error("User Updated with ID {} request update info but not found in database", id);
            this.create(userRequest);
            return null;
        }

        user.get().setUsername(userRequest.getUsername());
        user.get().setFirstname(userRequest.getFirstname());
        user.get().setLastname(userRequest.getLastname());
        user.get().setGender(userRequest.getGender());
        user.get().setDateOfBirth(userRequest.getDateOfBirth());
        user.get().setEmail(userRequest.getEmail());
        user.get().setPhoneNumber(userRequest.getPhoneNumber());
        user.get().setAddress(userRequest.getAddress());
        user.get().setUserType(userRequest.getUserType());
        log.info("Update user record: {}", user.get());

        userRepository.saveAndFlush(user.get());

        if (user.get().getDevices() != null) {
            var deviceRequest = userRequest.getDeviceRequest();
            if(StringUtils.hasText(deviceRequest.getDeviceId())){
                List<Device> devices = user.get().getDevices();
                Device deviceUpdate = devices.stream().filter(device ->
                        device.getDeviceId()
                                .equalsIgnoreCase(deviceRequest.getDeviceId())).findAny().orElse(null);

                if (deviceUpdate != null) {
                    deviceUpdate.setDeviceModel(deviceRequest.getDeviceModel());
                    deviceUpdate.setDeviceType(deviceRequest.getDeviceType());
                    deviceUpdate.setOsVersion(deviceRequest.getOsVersion());
                    deviceUpdate.setAppVersion(deviceRequest.getAppVersion());
                    deviceUpdate.setTrustDevice(deviceRequest.isTrustDevice());
                    log.info("Update device record: {}", deviceUpdate);
                    deviceRepository.saveAndFlush(deviceUpdate);
                }else {
                    Device device = userHandlerService.convertDeviceRequestToUserDevice(user.get(), deviceRequest);
                    log.info("Save new device record: {}", device);
                    deviceRepository.saveAndFlush(device);
                }
            }
        }

        return userHandlerService.convertUserToUserResponse(user.get());
    }

    @Override
    @Transactional
    public UserResponse findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            log.info("User with id {} not found in DB.", id);
            throw new UserNotFoundErrorException("User not found.");
        }
        return userHandlerService.convertUserToUserResponse(user.get());
    }


    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();

        if(users.isEmpty()){
            log.info("No user found in DB.");
            throw new UserNotFoundErrorException("User not found.");
        }

        List<UserResponse> userResponses = new ArrayList<>();
        for(User user : users) {
            UserResponse userResponse = userHandlerService.convertUserToUserResponse(user);
            userResponses.add(userResponse);
        }
        return userResponses;
    }
}

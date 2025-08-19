package org.example.ytlearning.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.ytlearning.dto.UserRequest;
import org.example.ytlearning.dto.UserResponse;
import org.example.ytlearning.repository.DeviceRepository;
import org.example.ytlearning.repository.UserRepository;
import org.example.ytlearning.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;

    public UserServiceImpl(UserRepository userRepository, DeviceRepository deviceRepository) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
    }


    @Override
    public UserResponse create(UserRequest userRequest) {
        return null;
    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        return null;
    }

    @Override
    public UserResponse findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<UserResponse> findAll() {
        return List.of();
    }
}

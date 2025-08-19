package org.example.ytlearning.service;

import org.example.ytlearning.dto.UserRequest;
import org.example.ytlearning.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse create(UserRequest userRequest);
    UserResponse update(Long id, UserRequest userRequest);
    UserResponse findById(Long id);
    void delete(Long id);
    List<UserResponse> findAll();
}

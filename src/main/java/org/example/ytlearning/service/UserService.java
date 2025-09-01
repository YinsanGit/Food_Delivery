package org.example.ytlearning.service;

import org.example.ytlearning.dto.UserRequest;
import org.example.ytlearning.dto.UserResponse;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface UserService {

    UserResponse create(UserRequest userRequest);
    UserResponse update(Long id, UserRequest userRequest);
    UserResponse findById(Long id);
    void delete(Long id);
    List<UserResponse> findAll() throws Throwable;
}

package com.phimmoi.techwizapi.service;

import com.phimmoi.techwizapi.dto.request.UserRequestDto;
import com.phimmoi.techwizapi.dto.response.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserResponseDto createUser(UserRequestDto request);
    Long deleteUser(Long id);
    Long activeUser(Long id);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long id);
}

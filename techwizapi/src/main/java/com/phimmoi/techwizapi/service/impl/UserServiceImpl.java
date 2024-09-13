package com.phimmoi.techwizapi.service.impl;

import com.phimmoi.techwizapi.dto.request.UserRequestDto;
import com.phimmoi.techwizapi.dto.response.UserResponseDto;
import com.phimmoi.techwizapi.entity.User;
import com.phimmoi.techwizapi.exception.BusinessException;
import com.phimmoi.techwizapi.repository.UserRepository;
import com.phimmoi.techwizapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto request) {

        if (userRepository.existsByUsername(request.getUsername().trim())) {
            throw new BusinessException("Username is already in use");
        }

        User user = new User();
        user.setUsername(request.getUsername().trim());
        user.setEmail(request.getEmail().trim());
        user.setPassword(request.getPassword().trim());
        user.setRoleId(1L);
        user.setIsDeleted(false);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        return UserResponseDto.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .build();
    }

    @Override
    public Long deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new BusinessException("User not found")
        );

        user.setIsDeleted(true);
        userRepository.save(user);
        return id;
    }

    @Override
    public Long activeUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new BusinessException("User not found")
        );

        user.setIsDeleted(false);
        userRepository.save(user);
        return id;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(user ->
             UserResponseDto.builder()
                     .id(user.getId())
                     .username(user.getUsername())
                     .email(user.getEmail())
                     .build()
        ).toList();
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User savedUser = userRepository.findById(id).orElseThrow(
                () -> new BusinessException("User not found")
        );
        return UserResponseDto.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .build();
    }
}

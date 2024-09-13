package com.phimmoi.techwizapi.controller;

import com.phimmoi.techwizapi.dto.ApiResponse;
import com.phimmoi.techwizapi.dto.request.UserRequestDto;
import com.phimmoi.techwizapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse.Response> register(@Valid @RequestBody UserRequestDto user) {
        return ApiResponse.created(userService.createUser(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse.Response> delete(@PathVariable("userId") Long userId) {
        return ApiResponse.ok(userService.deleteUser(userId));
    }

    @PostMapping("/{userId}/active")
    public ResponseEntity<ApiResponse.Response> active(@PathVariable("userId") Long userId) {
        return ApiResponse.ok(userService.activeUser(userId));
    }

    @GetMapping
    public ResponseEntity<ApiResponse.Response> getUsers() {
        return ApiResponse.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse.Response> getUser(@PathVariable("userId") Long userId) {
        return ApiResponse.ok(userService.getUserById(userId));
    }
}

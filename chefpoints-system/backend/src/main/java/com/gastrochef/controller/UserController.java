package com.gastrochef.controller;

import com.gastrochef.dto.ApiResponse;
import com.gastrochef.dto.LoginRequest;
import com.gastrochef.dto.RegisterRequest;
import com.gastrochef.model.User;
import com.gastrochef.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        try {
            User user = userService.registerUser(request.getName(), request.getEmail(), request.getPassword());
            return ResponseEntity.ok(new ApiResponse(true, "Registrierung erfolgreich", user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage(), null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request) {
        Optional<User> user = userService.loginUser(request.getEmail(), request.getPassword());
        if (user.isPresent()) {
            return ResponseEntity.ok(new ApiResponse(true, "Login erfolgreich", user.get()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse(false, "Ungültige Anmeldedaten", null));
        }
    }

    @GetMapping("/{userId}/points")
    public ResponseEntity<ApiResponse> getPoints(@PathVariable Long userId) {
        try {
            Integer points = userService.getChefPoints(userId);
            return ResponseEntity.ok(new ApiResponse(true, "ChefPoints abgerufen", points));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage(), null));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getUser(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok(new ApiResponse(true, "Benutzer gefunden", user.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

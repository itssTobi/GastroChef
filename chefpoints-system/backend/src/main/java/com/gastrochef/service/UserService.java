package com.gastrochef.service;

import com.gastrochef.model.User;
import com.gastrochef.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User registerUser(String name, String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email bereits registriert");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password); // TODO: Password hashing implementieren
        user.setChefPoints(0);

        return userRepository.save(user);
    }

    public Optional<User> loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User addPoints(Long userId, Integer points) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Benutzer nicht gefunden"));
        user.setChefPoints(user.getChefPoints() + points);
        return userRepository.save(user);
    }

    public Integer getChefPoints(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Benutzer nicht gefunden"));
        return user.getChefPoints();
    }
}

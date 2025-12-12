package com.amu.userservice.services;

import com.amu.userservice.entities.User;
import com.amu.userservice.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public boolean usernameExists(String username) {
        return repo.existsByUsername(username);
    }

    public boolean emailExists(String email) {
        return repo.existsByEmail(email);
    }

    @Transactional
    public User createUser(String username, String rawPassword, String email, String fullName) {
        User u = new User();
        u.setUsername(username);
        u.setPassword(encoder.encode(rawPassword));
        u.setEmail(email);
        u.setFullName(fullName);
        u.setRole("ROLE_USER");
        u.setIsEnabled(true);
        return repo.save(u);
    }

    public User findByUsername(String username) {
        return repo.findByUsername(username).orElse(null);
    }

    public User findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Transactional
    public User updateProfile(Long userId, String fullName, String email) {
        User u = repo.findById(userId).orElseThrow();
        u.setFullName(fullName);
        u.setEmail(email);
        return repo.save(u);
    }
}

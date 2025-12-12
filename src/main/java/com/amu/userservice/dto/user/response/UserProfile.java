package com.amu.userservice.dto.user.response;

public record UserProfile(Long id, String username, String email, String fullName, String role, Boolean isEnabled) {}
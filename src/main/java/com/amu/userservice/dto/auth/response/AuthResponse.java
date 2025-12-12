package com.amu.userservice.dto.auth.response;

public record AuthResponse(String token, String tokenType, Long expiresIn) {}
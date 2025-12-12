package com.amu.userservice.dto.auth.request;

public record RegisterRequest(String username, String password, String email, String fullName) {}
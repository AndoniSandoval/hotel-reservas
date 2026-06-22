package com.aa.auth.services;

import com.aa.auth.dto.LoginRequest;
import com.aa.auth.dto.TokenResponse;

public interface AuthService {

    TokenResponse autenticar(LoginRequest request) throws Exception;
}

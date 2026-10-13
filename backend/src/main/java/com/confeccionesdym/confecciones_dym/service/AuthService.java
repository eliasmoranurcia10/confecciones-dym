package com.confeccionesdym.confecciones_dym.service;

import com.confeccionesdym.confecciones_dym.dto.login.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}

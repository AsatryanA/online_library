package com.library.online_library.service.auth;

import com.library.online_library.model.dto.auth.AuthRequest;
import com.library.online_library.model.dto.auth.AuthResponseDTO;
import com.library.online_library.model.dto.auth.UserRegisterDTO;

public interface AuthService {
    AuthResponseDTO login(AuthRequest authRequest);

    AuthResponseDTO renew(String refreshToken);

    void register(UserRegisterDTO userRegisterDTO);

}

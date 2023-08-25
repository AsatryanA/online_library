package com.library.online_library.model.dto.auth;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class AuthResponseDTO {
    private String accessToken;

    private String refreshToken;

    private String tokenType = "Bearer";

    private LocalDateTime expiresIn;

    private Long userId;

    private List<String> roles;
}

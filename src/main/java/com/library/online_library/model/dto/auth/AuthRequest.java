package com.library.online_library.model.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}

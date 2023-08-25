package com.library.online_library.model.dto.user.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserAuthRequest {
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String code;
}

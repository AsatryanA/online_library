package com.library.online_library.model.dto.user.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendSmsRequest {
    @NotBlank
    private String phoneNumber;
}

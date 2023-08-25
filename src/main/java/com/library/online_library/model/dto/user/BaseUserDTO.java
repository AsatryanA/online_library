package com.library.online_library.model.dto.user;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BaseUserDTO {

    private String firstName;

    private String lastName;

    private LocalDateTime birthday;

    @Email
    private String email;
}

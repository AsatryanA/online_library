package com.library.online_library.model.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminRegisterDTO {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    private String phoneNumber;
    @NotNull
    @Email
    private String email;
    private String address;
    private String postalZip;
    @NotNull
    private String password;
    private String country;
}

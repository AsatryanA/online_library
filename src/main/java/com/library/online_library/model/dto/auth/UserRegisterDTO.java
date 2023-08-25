package com.library.online_library.model.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
public class UserRegisterDTO {


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
    private Set<Long> authorIds;
    private Set<Long> genreIds;
}

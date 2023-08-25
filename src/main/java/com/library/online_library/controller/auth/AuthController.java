package com.library.online_library.controller.auth;

import com.library.online_library.model.dto.auth.AuthRequest;
import com.library.online_library.model.dto.auth.AuthResponseDTO;
import com.library.online_library.model.dto.auth.UserRegisterDTO;
import com.library.online_library.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.library.online_library.utils.constants.AppConstants.API_V1;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1 + "/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
        authService.register(userRegisterDTO);
    }

    @PostMapping("/renew")
    public ResponseEntity<AuthResponseDTO> renew(@RequestHeader(name = AUTHORIZATION) String refreshToken) {
        return ResponseEntity.ok(authService.renew(refreshToken));
    }
}


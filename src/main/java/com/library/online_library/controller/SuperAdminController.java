package com.library.online_library.controller;

import com.library.online_library.model.dto.auth.AdminRegisterDTO;
import com.library.online_library.model.dto.user.UserDTO;
import com.library.online_library.service.super_admin.SuperAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.library.online_library.utils.constants.AppConstants.API_V1;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1 + "/super")
public class SuperAdminController {

    private final SuperAdminService superAdminService;

    @PostMapping("/admin")
    @PreAuthorize("hasRole(@role.ROLE_SUPER_ADMIN)")
    public ResponseEntity<UserDTO> createAdmin(@RequestBody @Valid AdminRegisterDTO adminRegisterDTO) {
        return ResponseEntity.ok(superAdminService.register(adminRegisterDTO));
    }

}

package com.library.online_library.controller.user;

import com.library.online_library.model.dto.user.UserDTO;
import com.library.online_library.model.dto.user.UserUpdateRequest;
import com.library.online_library.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.library.online_library.utils.constants.AppConstants.API_V1;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1 + "/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/self")
    @PreAuthorize("hasRole(@role.ROLE_USER)")
    public ResponseEntity<UserDTO> selfGet() {
        return ResponseEntity.ok(userService.selfGet());
    }

    @PutMapping("/self")
    public ResponseEntity<UserDTO> selfUpdate(@RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        return ResponseEntity.ok(userService.selfUpdate(userUpdateRequest));
    }

    @DeleteMapping("/self")
    @PreAuthorize("hasRole(@role.ROLE_USER)")
    public void selfDelete() {
        userService.selfDelete();
    }

}

package com.library.online_library.model.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UserDTO extends BaseUserDTO {
    private Long id;
    private LocalDateTime createdAt;
    private String phoneNumber;
    private String imageUrl;
}

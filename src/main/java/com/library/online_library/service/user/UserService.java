package com.library.online_library.service.user;

import com.library.online_library.model.dto.user.UserDTO;
import com.library.online_library.model.dto.user.UserUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface UserService {

    Page<UserDTO> getAll(PageRequest pageRequest);

    UserDTO selfUpdate(UserUpdateRequest userUpdateRequest);

    void selfDelete();

    UserDTO selfGet();
}

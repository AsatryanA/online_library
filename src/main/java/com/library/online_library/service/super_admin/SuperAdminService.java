package com.library.online_library.service.super_admin;

import com.library.online_library.model.dto.auth.AdminRegisterDTO;
import com.library.online_library.model.dto.user.UserDTO;

public interface SuperAdminService {

    UserDTO register (AdminRegisterDTO adminRegisterDTO);
}

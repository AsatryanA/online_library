package com.library.online_library.utils.constants;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("role")
@RequiredArgsConstructor
public class RoleNames {

    public static final String ROLE_SUPER_ADMIN = RoleEnum.ROLE_SUPER_ADMIN.getName();
    public static final String ROLE_ADMIN = RoleEnum.ROLE_ADMIN.getName();
    public static final String ROLE_USER = RoleEnum.ROLE_USER.getName();

}
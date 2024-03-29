package com.library.online_library.utils;

import com.library.online_library.service.UserDetailsImpl;
import com.library.online_library.utils.constants.RoleEnum;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class CurrentUser {

    public static Long getId() {
        var principal = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return principal.getId();
    }

    public static String getRole() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .map(Object::toString)
                .toList().get(0);
    }

    public static boolean isAdmin() {
        return getRole().equals(RoleEnum.ROLE_ADMIN.name());
    }

    public static boolean isUser() {
        return getRole().equals(RoleEnum.ROLE_USER.name());
    }
}

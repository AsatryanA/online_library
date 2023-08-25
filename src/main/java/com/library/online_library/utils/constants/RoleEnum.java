package com.library.online_library.utils.constants;

public enum RoleEnum {
    ROLE_SUPER_ADMIN("ROLE_SUPER_ADMIN"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private final String name;

    RoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

package com.library.online_library.utils.pagination.sort.enums;

public enum UserSortField {

    ID("id"),
    EMAIL("email"),
    FIRST_NAME("firstName"),
    PHONE_NUMBER("phoneNumber"),
    CREATED_AT("createdAt");

    private final String name;

    UserSortField(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

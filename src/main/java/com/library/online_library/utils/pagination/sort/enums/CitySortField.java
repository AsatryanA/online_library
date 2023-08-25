package com.library.online_library.utils.pagination.sort.enums;

public enum CitySortField {

    ID("id"),
    NAME("name"),
    CREATED_AT("createdAt"),
    DELIVERY_PRICE("deliveryPrice");

    private final String name;

    CitySortField(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

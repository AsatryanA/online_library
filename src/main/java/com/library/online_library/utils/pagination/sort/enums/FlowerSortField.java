package com.library.online_library.utils.pagination.sort.enums;

public enum FlowerSortField {
    ORDER_NUMBER("orderNumber");

    private final String name;

    FlowerSortField(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

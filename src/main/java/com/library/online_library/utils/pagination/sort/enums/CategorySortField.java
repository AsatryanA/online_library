package com.library.online_library.utils.pagination.sort.enums;

public enum CategorySortField {

    ID("id"),
    NAME("name");

    private final String name;

    CategorySortField(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

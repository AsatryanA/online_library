package com.library.online_library.utils.pagination.sort.enums;

public enum ColorSortField {

    ID("id"),
    NAME("name");

    private final String name;

    ColorSortField(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

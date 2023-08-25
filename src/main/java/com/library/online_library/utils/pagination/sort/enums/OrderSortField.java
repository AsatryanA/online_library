package com.library.online_library.utils.pagination.sort.enums;

public enum OrderSortField {

    ID("id"),
    CREATED_AT("createdAt"),
    DELIVER_ON("deliverOn"),
    DELIVERED_ON("deliveredOn"),
    RECEIVER_NAME("receiverName"),
    RECEIVER_PHONE_NUMBER("receiverPhoneNumber"),
    STATUS("status"),
    SHOP("shop"),
    TOTAL_PREPARATION_TIME("totalPreparationTime"),
    TOTAL_PRICE("totalAmount");

    private final String name;

    OrderSortField(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

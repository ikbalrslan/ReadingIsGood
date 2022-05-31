package com.getir.bookretail.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    DELIVERED(1),
    UNDELIVERED(2);

    private final int intValue;

    public static OrderStatus fromIntValue(int intValue) {
        for (OrderStatus orderStatus : values()) {
            if (orderStatus.intValue == intValue) {
                return orderStatus;
            }
        }
        return null;
    }

    public int intValue() {
        return intValue;
    }
}

package com.example.ecommerce_payment_gateway.util;


import java.time.LocalDateTime;

public class DateTimeUtil {
    private DateTimeUtil() {}
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }
}
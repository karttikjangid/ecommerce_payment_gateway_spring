package com.example.ecommerce_payment_gateway.util;


public class AmountUtil {
    private AmountUtil() {}
    public static int toPaise(Double amount) {
        return (int) Math.round(amount * 100);
    }
}
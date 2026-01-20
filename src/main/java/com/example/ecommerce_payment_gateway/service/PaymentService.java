package com.example.ecommerce_payment_gateway.service;

import com.example.ecommerce_payment_gateway.exception.ResourceNotFoundException;
import com.example.ecommerce_payment_gateway.model.Order;
import com.example.ecommerce_payment_gateway.model.Payment;
import com.example.ecommerce_payment_gateway.repository.OrderRepository;
import com.example.ecommerce_payment_gateway.repository.PaymentRepository;
import com.example.ecommerce_payment_gateway.util.AmountUtil;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    private final RazorpayClient razorpayClient;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public PaymentService(RazorpayClient razorpayClient,
                          OrderRepository orderRepository,
                          PaymentRepository paymentRepository) {
        this.razorpayClient = razorpayClient;
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    public Payment createPayment(String orderId) throws Exception {

        // 1. Validate order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found"));

        // 2. Create Razorpay order
        JSONObject options = new JSONObject();
        options.put("amount", AmountUtil.toPaise(order.getTotalAmount()));
        options.put("currency", "INR");
        options.put("receipt", order.getId());

        com.razorpay.Order razorpayOrder =
                razorpayClient.orders.create(options);

        // 3. Save payment in DB
        Payment payment = new Payment();
        payment.setOrderId(order.getId());
        payment.setRazorpayOrderId(razorpayOrder.get("id"));
        payment.setAmount(order.getTotalAmount());
        payment.setStatus("CREATED");
        payment.setCreatedAt(LocalDateTime.now());

        return paymentRepository.save(payment);
    }
}

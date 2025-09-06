package org.example;

import java.time.LocalDateTime;



public class Payment {
    private Long id;
    private Order order;
    private PaymentMethod method;
    private double amount;
    private PaymentStatus status;
    private LocalDateTime paymentDate;

    public Payment() {
        this.paymentDate = LocalDateTime.now();
        this.status = PaymentStatus.PENDING;
    }

    public Payment(Order order, PaymentMethod method, double amount) {
        this();
        this.order = order;
        this.method = method;
        this.amount = amount;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public PaymentMethod getMethod() { return method; }
    public void setMethod(PaymentMethod method) { this.method = method; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }

    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }

    // Business methods
    public void processPayment() {
        // Simulate payment processing
        try {
            Thread.sleep(1000); // Simulate processing time
            this.status = PaymentStatus.COMPLETED;
            System.out.println("Payment processed successfully for amount: $" + amount);
        } catch (InterruptedException e) {
            this.status = PaymentStatus.FAILED;
            System.out.println("Payment processing failed");
        }
    }
}
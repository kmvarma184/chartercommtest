package com.test.rewards.model;

import lombok.Data;

@Data
public class UserPayment {

    private String userId;
    private Money payment;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Money getPayment() {
        return payment;
    }

    public void setPayment(Money payment) {
        this.payment = payment;
    }
}

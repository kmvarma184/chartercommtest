package com.test.rewards.model;

import lombok.Data;

@Data
public class Customer {

    private String userId;

    public Customer(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

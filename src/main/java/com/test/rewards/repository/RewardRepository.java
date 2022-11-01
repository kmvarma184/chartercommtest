package com.test.rewards.repository;

import com.test.rewards.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RewardRepository {

    private Map<String, Double> cache = new HashMap<>();
    public int add(Customer customer, Double amount) {
        cache.put(customer.getUserId(), cache.getOrDefault(customer.getUserId(), 0.0) + amount);
        return 1;
    }

    public Double getByUser(Customer customer) {
        return cache.get(customer.getUserId());
    }
}

package com.test.rewards.repository;

import com.test.rewards.model.Customer;
import com.test.rewards.model.Money;
import com.test.rewards.model.UserPayment;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class RewardRepository {

    // User Id is the main key
    // Date is the sort key
    // Since this being just an assignment, making it work, please don't expect this to be production ready,
    // that will take more time.
    private Map<String, TreeMap<Long, Double>> cache = new HashMap<>();

    // Kept as LocalDate and not LocalDateTime intentionally for simpler usage.
    public int add(String userId, Double amount, LocalDateTime dateOfTransaction) {
        TreeMap<Long, Double> existing = null;
        if (cache.containsKey(userId)) {
            existing = cache.get(userId);
        }
        if (existing == null) {
            existing = new TreeMap<>();
        }
        existing.put(Timestamp.valueOf(dateOfTransaction).getTime(), amount);
        cache.put(userId, existing);
        return existing.size();
    }

    public Collection<Double> getByUserAndRange(String userId, LocalDateTime startTime, LocalDateTime endTime) {
        if (cache.containsKey(userId)) {
            TreeMap<Long, Double> existing = cache.get(userId);
            Long startTimeKey = existing.floorKey(Timestamp.valueOf(startTime).getTime());
            Long endTimeKey = existing.floorKey(Timestamp.valueOf(endTime).getTime());
            SortedMap<Long, Double> sortedMap = existing.subMap(startTimeKey, endTimeKey);
            return sortedMap.values();
        }
        return null;
    }

    public Collection<Double> getByUser(String userId) {
        if (cache.containsKey(userId)) {
            return cache.get(userId).values();
        }
        return null;
    }
}

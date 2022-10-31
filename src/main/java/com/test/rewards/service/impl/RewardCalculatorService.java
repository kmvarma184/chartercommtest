package com.test.rewards.service.impl;

import org.springframework.stereotype.Service;

@Service
public class RewardCalculatorService {

    private final int firstStage = 50;
    private final int firstPointer = 1;
    private final int secondStage = 100;
    private final int secondPointer = 1;

    public int calculate(Double amount) {
        int points = 0;
        if (amount < 50) {
            return points;
        }
        points += (int)(amount - firstStage) * firstPointer;
        points += (int)(amount - secondStage) * secondPointer;
        return points;
    }
}

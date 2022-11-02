package com.test.rewards.service.impl;

import com.test.rewards.exceptions.BusinessException;
import com.test.rewards.model.Customer;
import com.test.rewards.model.Points;
import com.test.rewards.model.UserPayment;
import com.test.rewards.model.UserPoints;
import com.test.rewards.repository.RewardRepository;
import com.test.rewards.repository.UserRepository;
import com.test.rewards.service.UserRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Service
public class UserUserRewardServiceImpl implements UserRewardService {

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RewardCalculatorService rewardCalculatorService;

    @Override
    public UserPoints calculateReward(String userId) throws BusinessException {
        // check for user validations from userRepository here.
        // Add Metrics start here or use annotations
        int points = 0;
        try {
            Collection<Double> amountCollection = rewardRepository.getByUser(userId);
            Double totalAmount = amountCollection.stream().reduce(0.0, (a, b) -> a + b);
            points = rewardCalculatorService.calculate(totalAmount);
            // Add Metrics end here

        } catch (Exception e) {
            // add the retry logic if needed here. or generic method for that in one place.
            throw new BusinessException(e.toString());
        }
        return new UserPoints(userId, new Points(points));
    }

    // Can be extended to use to get points between any 2 dates.
    @Override
    public UserPoints calculateReward(String userId, int months) throws BusinessException {
        int points = 0;
        try {
            LocalDateTime startDate = LocalDateTime.now().minusMonths(3);
            LocalDateTime endDate = LocalDateTime.now();
            Collection<Double> amountCollection = rewardRepository.getByUserAndRange(userId, startDate, endDate);
            if (CollectionUtils.isEmpty(amountCollection)) {
                throw new BusinessException("empty");
            }
            Double totalAmount = amountCollection.stream().reduce(0.0, (a, b) -> a + b);
            points = rewardCalculatorService.calculate(totalAmount);
            // Add Metrics end here

        } catch (Exception e) {
            // add the retry logic if needed here. or generic method for that in one place.
            throw new BusinessException(e.toString());
        }
        return new UserPoints(userId, new Points(points));
    }

    @Override
    public UserPayment addUserTransaction(UserPayment userPayment) {
        // do validations here. not doing for the assignment.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime localDate = LocalDateTime.parse(userPayment.getDateTime(), formatter);
        rewardRepository.add(userPayment.getUserId(), userPayment.getPayment().getAmount(), localDate);
        return userPayment;
    }
}

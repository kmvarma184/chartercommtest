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
            Double amount = rewardRepository.getByUser(new Customer(userId));
            points = rewardCalculatorService.calculate(amount);
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
        rewardRepository.add(new Customer(userPayment.getUserId()), userPayment.getPayment().getAmount());
        return userPayment;
    }
}

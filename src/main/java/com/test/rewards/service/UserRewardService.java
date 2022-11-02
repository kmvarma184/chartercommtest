package com.test.rewards.service;

import com.test.rewards.exceptions.BusinessException;
import com.test.rewards.model.UserPayment;
import com.test.rewards.model.UserPoints;

public interface UserRewardService {

    public UserPoints calculateReward(String userId) throws BusinessException;

    public UserPoints calculateReward(String userId, int months) throws BusinessException;

    public UserPayment addUserTransaction(UserPayment userPayment);
}

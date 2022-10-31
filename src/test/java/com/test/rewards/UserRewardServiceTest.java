package com.test.rewards;

import com.test.rewards.exceptions.BusinessException;
import com.test.rewards.model.Money;
import com.test.rewards.model.UserPayment;
import com.test.rewards.model.UserPoints;
import com.test.rewards.repository.RewardRepository;
import com.test.rewards.service.impl.RewardCalculatorService;
import com.test.rewards.service.impl.UserUserRewardServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


@SpringBootTest
public class UserRewardServiceTest {

    @InjectMocks
    private UserUserRewardServiceImpl userRewardService;

    @Mock
    private RewardRepository rewardRepository;

    @Mock
    private RewardCalculatorService rewardCalculatorService;

    @Test
    void testAddTransaction() throws BusinessException {
        UserPayment userPayment = new UserPayment();
        userPayment.setUserId("user1");
        userPayment.setPayment(new Money(57.0));

        userRewardService.addUserTransaction(userPayment);
        userPayment.setPayment(new Money(98.0));
        userRewardService.addUserTransaction(userPayment);

        UserPoints userPoints = userRewardService.calculateReward("user1");
        Assert.notNull(userPoints);
    }
}

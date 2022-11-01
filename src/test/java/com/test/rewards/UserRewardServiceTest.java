package com.test.rewards;

import com.test.rewards.exceptions.BusinessException;
import com.test.rewards.model.Customer;
import com.test.rewards.model.Money;
import com.test.rewards.model.UserPayment;
import com.test.rewards.model.UserPoints;
import com.test.rewards.repository.RewardRepository;
import com.test.rewards.service.impl.RewardCalculatorService;
import com.test.rewards.service.impl.UserUserRewardServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;


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
        userPayment.setPayment(new Money(60.0));
        userRewardService.addUserTransaction(userPayment);
        userPayment.setPayment(new Money(40.0));
        userRewardService.addUserTransaction(userPayment);
        Mockito.when(rewardRepository.add(Mockito.any(Customer.class), Mockito.any(Double.class))).thenReturn(1);
        Mockito.when(rewardRepository.getByUser(Mockito.any(Customer.class))).thenReturn(100.0);
        Mockito.when(rewardCalculatorService.calculate(Mockito.any(Double.class))).thenReturn(2);

        UserPoints userPoints = userRewardService.calculateReward("user1");
        Assert.assertEquals(userPoints.getPoints().getPoints(), 2);
    }
}

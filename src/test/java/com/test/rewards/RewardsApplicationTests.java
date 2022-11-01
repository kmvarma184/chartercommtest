package com.test.rewards;

import com.test.rewards.controller.RewardController;
import com.test.rewards.model.Money;
import com.test.rewards.model.UserPayment;
import com.test.rewards.model.UserPoints;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

@SpringBootTest
class RewardsApplicationTests {

	@InjectMocks
	private RewardController rewardController;

	@Test
	void contextLoads() {

	}

	@Test
	void testAddTransaction() {
		UserPayment userPayment = new UserPayment();
		userPayment.setUserId("user1");
		userPayment.setPayment(new Money(57.0));
		rewardController.addUserTransaction(userPayment);
		userPayment.setPayment(new Money(98.0));
		rewardController.addUserTransaction(userPayment);

		ResponseEntity<UserPoints> responseEntity = rewardController.fetchUserPoints("user1");
	}

}

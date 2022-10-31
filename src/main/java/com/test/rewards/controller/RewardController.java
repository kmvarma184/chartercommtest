package com.test.rewards.controller;

import com.test.rewards.exceptions.BusinessException;
import com.test.rewards.model.UserPayment;
import com.test.rewards.model.UserPoints;
import com.test.rewards.service.UserRewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reward")
public class RewardController {

    @Autowired
    private UserRewardService userRewardService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserPoints> fetchUserPoints(@RequestParam("userId") String userId) {
        if (StringUtils.isEmpty(userId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserPoints userPoints = null;
        try {
             userPoints = userRewardService.calculateReward(userId);
        } catch (BusinessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userPoints, HttpStatus.OK);
    }

    @PostMapping()
    public void addUserTransaction(@RequestBody UserPayment userPayment) {
        // not adding validations intentionally here.
        userRewardService.addUserTransaction(userPayment);
    }
}
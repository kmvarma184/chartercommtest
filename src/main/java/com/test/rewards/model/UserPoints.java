package com.test.rewards.model;

public class UserPoints {

    public UserPoints(String userId, Points points) {
        this.userId = userId;
        this.points = points;
    }

    private String userId;
    private Points points;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }
}

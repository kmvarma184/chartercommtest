package com.test.rewards.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Points {

    private int points;

    public Points(int points) {
        this.points = points;
    }
}

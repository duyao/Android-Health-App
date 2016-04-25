package example.dy.com.homework;

import java.util.Date;

import example.dy.com.homework.entity.JsonUser;

/**
 * Created by dy on 2016/4/25.
 */
public class JsonReport {
    private String id;
    private String time;
    private double consumed;
    private double intaked;
    private double totalSteps;
    private double calorieSetGoal;
    private double remaining;
    private JsonUser userId;

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public double getConsumed() {
        return consumed;
    }

    public double getIntaked() {
        return intaked;
    }

    public double getTotalSteps() {
        return totalSteps;
    }

    public double getCalorieSetGoal() {
        return calorieSetGoal;
    }

    public double getRemaining() {
        return remaining;
    }

    public JsonUser getUserId() {
        return userId;
    }
}

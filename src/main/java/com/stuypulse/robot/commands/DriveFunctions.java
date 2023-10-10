package com.stuypulse.robot.commands;

import com.stuypulse.robot.util.Motor;

public class DriveFunctions {
    
    public static void driveForwards(Motor left, Motor right) {
        left.set(1.0);
        right.set(1.0);
    }

    public static void driveBackwards(Motor left, Motor right) {
        left.set(-1.0);
        right.set(-1.0);
    }

    public static void turnRight(Motor left, Motor right) {
        left.set(1.0);
        right.set(-1.0);
    }

    public static void turnLeft(Motor left, Motor right) {
        right.set(1.0);
        left.set(-1.0);
    }

    public static void arcRight(Motor left, Motor right) {
        left.set(1.0);
        right.set(0.5);
    }

    public static void arcLeft(Motor left, Motor right) {
        right.set(1.0);
        left.set(0.5);
    }

    public static void stopDistance(Motor left, Motor right) {
        if (left.getDistance() < 8.0 && right.getDistance() < 8.0) {
            right.set(1.0);
            left.set(1.0);
        }
        else {
            left.set(0.0);
            right.set(0.0);
        }
    }

    public static void bangBang(Motor left, Motor right) {
        if (left.getDistance() < 24.0 && right.getDistance() < 24.0) driveBackwards(left, right);
        else if (left.getDistance() > 24.0 && right.getDistance() > 24.0) driveForwards(left, right);
    }

    public static void betterControl(Motor left, Motor right) {
        if (left.getDistance() <=  10.0) driveForwards(left, right);
        else if (left.getDistance() <=  20.0 && left.getDistance() > 10.0) {
            left.set(0.5);
            right.set(0.5);
        }
        else bangBang(left, right);
    }
}
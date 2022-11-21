package com.stuypulse.robot.commands;

import com.stuypulse.robot.util.Motor;

public class DriveFunctions {
    
    public static void driveForwards(Motor left, Motor right) {
        left.set(1);
        right.set(1);
    }

    public static void driveBackwards(Motor left, Motor right) {
        left.set(-0.8);
        right.set(-0.8);
    }

    public static void turnRight(Motor left, Motor right) {
        right.set(-1);
        left.set(1);
    }

    public static void turnLeft(Motor left, Motor right) {
        right.set(1);
        left.set(-1);
    }

    public static void arcRight(Motor left, Motor right) {
        left.set(0.75);
        right.set(0.25);
    }

    public static void arcLeft(Motor left, Motor right) {}

    public static void stopDistance(Motor left, Motor right) {
        left.set(0.8);
        right.set(0.8);

        if (left.getDistance() >= 120.0 && right.getDistance() >= 120.0) {
            left.set(0.0);
            right.set(0.0);//heheheha skill issue L
        }
    }

    public static void bangBang(Motor left, Motor right) {
        if(left.getDistance() + right.getDistance() > 240) {
            left.set(-1.0);
            right.set(-1.0);
        }
        else if(left.getDistance() + right.getDistance() < 240 ) {
            left.set(1.0);
            right.set(1.0);
        }
        else {
            left.set(0);
            right.set(0); 
        }
    }

    public static void betterControl(Motor left, Motor right) {
        double kP = 2.0;
        double error = 120 - left.getDistance();

        left.set(kP * error);
        right.set(kP * error);
    }

}

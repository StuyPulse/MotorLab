package com.stuypulse.robot.util;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class Motor extends SubsystemBase {
    // set speed from -1.0 to 1.0
    public abstract void set(double speed);

    // speed in inches/s
    public abstract double getSpeed();

    // distance in inches
    public abstract double getDistance();
}

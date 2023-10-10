package com.stuypulse.robot.util;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.stuypulse.robot.constants.Settings.Limits;

public class CANSparkMaxes extends Motor {

    private CANSparkMax[] motors;
    private RelativeEncoder encoder;

    public CANSparkMaxes(RelativeEncoder encoder, CANSparkMax... motors) {
        this.motors = motors;
        this.encoder = encoder;
    }

    @Override
    public void set(double speed) {
        for (CANSparkMax motor : motors) {
            motor.setVoltage(speed * Limits.CAN_MAX_VOLTS);
        }
    }

    @Override
    public double getSpeed() {
        return encoder.getVelocity();
    }

    @Override
    public double getDistance() {
        return encoder.getPosition();
    }

}

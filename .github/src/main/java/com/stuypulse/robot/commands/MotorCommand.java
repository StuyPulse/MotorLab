package com.stuypulse.robot.commands;

import java.util.function.BiConsumer;

import com.stuypulse.robot.subsystems.Robot;
import com.stuypulse.robot.util.Motor;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class MotorCommand extends CommandBase {

    private Robot robot;
    private BiConsumer<Motor, Motor> motorFunc;

    public MotorCommand(Robot robot, BiConsumer<Motor, Motor> motorFunc) {
        this.robot = robot;
        this.motorFunc = motorFunc;

        addRequirements(robot);
    }

    @Override
    public void initialize() {
        robot.reset(new Pose2d());
    }

    @Override
    public void execute() {
        motorFunc.accept(robot.getLeftMotor(), robot.getRightMotor());
    }

    @Override
    public void end(boolean interrupted) {
        robot.getLeftMotor().set(0);
        robot.getRightMotor().set(0);
    }
    
}

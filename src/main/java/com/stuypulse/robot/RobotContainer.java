/************************ PROJECT PHIL ************************/
/* Copyright (c) 2022 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/

package com.stuypulse.robot;

import com.stuypulse.robot.commands.DriveFunctions;
import com.stuypulse.robot.commands.MotorCommand;
import com.stuypulse.robot.subsystems.SimRomi;
import com.stuypulse.robot.subsystems.Robot;
import com.stuypulse.robot.subsystems.Romi;
import com.stuypulse.stuylib.input.Gamepad;
import com.stuypulse.stuylib.input.gamepads.keyboard.SimKeyGamepad;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {

    // Gamepads
    public final Gamepad driver = new SimKeyGamepad();

    private Robot robot = RobotBase.isReal() ? new Romi() : new SimRomi();

    // Autons
    private static SendableChooser<Command> autonChooser = new SendableChooser<>();

    // Robot container

    public RobotContainer() {
        configureDefaultCommands();
        configureButtonBindings();
        configureAutons();
    }

    /****************/
    /*** DEFAULTS ***/
    /****************/

    private void configureDefaultCommands() {}

    /***************/
    /*** BUTTONS ***/
    /***************/

    private void configureButtonBindings() {
        driver.getDPadUp().whileTrue(new MotorCommand(robot, DriveFunctions::driveForwards));
    }

    /**************/
    /*** AUTONS ***/
    /**************/

    public void configureAutons() {
        autonChooser.setDefaultOption("Drive Backwards", new MotorCommand(robot, DriveFunctions::driveBackwards));
        autonChooser.addOption("Drive Forwards", new MotorCommand(robot, DriveFunctions::driveForwards));
        autonChooser.addOption("Turn Right", new MotorCommand(robot, DriveFunctions::turnRight));
        autonChooser.addOption("Turn Left", new MotorCommand(robot, DriveFunctions::turnLeft));
        autonChooser.addOption("Arc Right", new MotorCommand(robot, DriveFunctions::arcRight));
        autonChooser.addOption("Arc Left", new MotorCommand(robot, DriveFunctions::arcLeft));
        autonChooser.addOption("Stop Distance", new MotorCommand(robot, DriveFunctions::stopDistance));
        autonChooser.addOption("Bang-bang", new MotorCommand(robot, DriveFunctions::bangBang));
        autonChooser.addOption("Better Control", new MotorCommand(robot, DriveFunctions::betterControl));
        autonChooser.addOption("Bestest Control", new MotorCommand(robot, DriveFunctions::bestestControl));

        SmartDashboard.putData("Autonomous", autonChooser);
    }

    public Command getAutonomousCommand() {
        return autonChooser.getSelected();
    }
}

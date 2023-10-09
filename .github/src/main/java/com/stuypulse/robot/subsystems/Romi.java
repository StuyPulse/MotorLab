// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.stuypulse.robot.subsystems;

import static com.stuypulse.robot.constants.Ports.Romi.*;
import static com.stuypulse.robot.constants.Settings.Romi.Robot.*;

import com.stuypulse.robot.constants.Settings;

import static com.stuypulse.robot.constants.Settings.Romi.Encoder.*;

import com.stuypulse.robot.util.Motor;
import com.stuypulse.robot.util.RomiMotor;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.romi.RomiGyro;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Romi extends Robot {
  
  private Spark leftMotor = new Spark(LEFT_MOTOR);
  private Spark rightMotor = new Spark(RIGHT_MOTOR);

  private Encoder leftEncoder = new Encoder(LEFT_ENCODER_A, LEFT_ENCODER_B);
  private Encoder rightEncoder = new Encoder(RIGHT_ENCODER_A, RIGHT_ENCODER_B);

  private final DifferentialDriveOdometry odometry;
  private final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(TRACK_WIDTH_METERS);
  
  private final RomiGyro gyro = new RomiGyro();

  private final Field2d field = new Field2d();

  private final Motor left, right;

  /** Creates a new RomiDrivetrain. */
  public Romi() {
    leftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
    rightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);

    leftEncoder.reset();
    rightEncoder.reset();

    // Invert right side since motor is flipped
    rightMotor.setInverted(true);

    left = new RomiMotor(leftMotor, leftEncoder);
    right = new RomiMotor(rightMotor, rightEncoder);

    odometry = new DifferentialDriveOdometry(getRotation2d(), 0, 0);

    SmartDashboard.putData(field);
  }

  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }
  
  @Override
  public void reset(Pose2d pose) {
    leftEncoder.reset();
    rightEncoder.reset();

    odometry.resetPosition(new Rotation2d(), 0, 0, pose);
  }

  public double getRadians() {
    return (rightEncoder.getDistance() - rightEncoder.getDistance()) / TRACK_WIDTH_METERS;
  }

  public Rotation2d getRotation2d() {
    // inverted to keep in convention of ccw positive
    return new Rotation2d(-getRadians());
  }

  @Override
  public Motor getLeftMotor() {
    return left;
  }

  @Override
  public Motor getRightMotor() {
    return right;
  }

  @Override
  public void periodic() {

    odometry.update(getRotation2d(), leftEncoder.getDistance(), rightEncoder.getDistance());
    field.setRobotPose(getPose().plus(Settings.Field.FIELD_OFFSET));
  }
}

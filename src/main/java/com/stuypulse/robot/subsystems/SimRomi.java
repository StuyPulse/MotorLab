package com.stuypulse.robot.subsystems;

import static com.stuypulse.robot.constants.Settings.Romi.Robot.*;

import com.stuypulse.robot.util.Motor;
import com.stuypulse.robot.util.SimMotor;

import static com.stuypulse.robot.constants.Settings.Romi.Feedforward.*;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.Nat;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N7;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SimRomi extends Robot {

	private final DifferentialDrivetrainSim sim;

	private double leftVoltage, rightVoltage;

	private final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(TRACK_WIDTH_METERS);
	private final DifferentialDriveOdometry odometry;

	private final Field2d field;

	public SimRomi() {
		sim = new DifferentialDrivetrainSim(
			LinearSystemId.identifyDrivetrainSystem(
				kV,
				kA,
				10, 5),
			DCMotor.getRomiBuiltIn(2),
			1.0,
			TRACK_WIDTH_METERS,
			WHEEL_DIAMETER_METERS / 2,
			new Matrix<N7, N1>(Nat.N7(), Nat.N1()));

		odometry = new DifferentialDriveOdometry(getRotation2d());

		field = new Field2d();
		SmartDashboard.putData(field);
	}
	
	public Field2d getField2d() {
		return field;
	}

	public Pose2d getPose() {
		return sim.getPose();
	}

	public void setPose(Pose2d pose) {
		sim.setPose(pose);

		odometry.resetPosition(pose, new Rotation2d());
	}

	public Rotation2d getRotation2d() {
		return sim.getHeading();
	}

	public void setLeftVoltgage(double volts) {
		leftVoltage = volts;
	}

	public void setRightVoltgage(double volts) {
		rightVoltage = volts;
	}

	private static double clamp(double x) {
		return MathUtil.clamp(x, -RoboRioSim.getVInVoltage(), +RoboRioSim.getVInVoltage());
	}

	@Override
	public Motor getLeftMotor() {
		return new SimMotor(this::setLeftVoltgage, sim::getLeftVelocityMetersPerSecond, sim::getLeftPositionMeters);
	}

	@Override
	public Motor getRightMotor() {
		return new SimMotor(this::setRightVoltgage, sim::getRightVelocityMetersPerSecond, sim::getRightPositionMeters);
	}

	@Override
	public void stop() {
		leftVoltage = 0;
		rightVoltage = 0;
	}

	@Override
	public void periodic() {
	  odometry.update(getRotation2d(), sim.getLeftPositionMeters(), sim.getRightPositionMeters());
  
		sim.setInputs(
			clamp(leftVoltage),
			clamp(rightVoltage)
		);

		sim.update(0.02);

		field.setRobotPose(getPose());

		SmartDashboard.putNumber("Left Velocity", sim.getLeftVelocityMetersPerSecond());
		SmartDashboard.putNumber("Right Velocity", sim.getRightVelocityMetersPerSecond());
	}
	
}

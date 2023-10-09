/************************ PROJECT PHIL ************************/
/* Copyright (c) 2022 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/

package com.stuypulse.robot.constants;

import com.stuypulse.stuylib.network.SmartBoolean;
import com.stuypulse.stuylib.network.SmartNumber;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;

/*-
 * File containing tunable settings for every subsystem on the robot.
 *
 * We use StuyLib's SmartNumber / SmartBoolean in order to have tunable
 * values that we can edit on Shuffleboard.
 */
public interface Settings {
    
    public interface Limits {
        int CAN_MAX_VOLTS = 8;

        int ROMI_MAX_VOLTS = 8;
        
    }

    public interface Field {
        Transform2d FIELD_OFFSET = new Transform2d(new Translation2d(0, 0), new Rotation2d(0));
    }

    public interface Romi {
        SmartNumber SET_DELAY = new SmartNumber("Romi/Set Delay", 0.1);
        
        public interface Robot {
            double TRACK_WIDTH_METERS = 0.141;
            double WHEEL_DIAMETER_METERS = Units.metersToInches(0.070);
        }
        
        public interface Encoder {
            double COUNTS_PER_REVOLUTION = 1440.0;

            double DISTANCE_PER_PULSE = (Math.PI * Robot.WHEEL_DIAMETER_METERS) / COUNTS_PER_REVOLUTION;
        }
        
        public interface Feedforward {
            double kS = 0.45;
            double kV = 10.00;
            double kA = 0.186;
        }
    }
}

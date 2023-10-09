/************************ PROJECT PHIL ************************/
/* Copyright (c) 2022 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/

package com.stuypulse.robot.constants;

/** This file contains the different ports of motors, solenoids and sensors */
public interface Ports {
    public interface Romi {
        int LEFT_MOTOR = 0;
		int RIGHT_MOTOR = 1;

		int LEFT_ENCODER_A = 4;
		int LEFT_ENCODER_B = 5;

		int RIGHT_ENCODER_A = 6;
		int RIGHT_ENCODER_B = 7;
    }
    
    public interface Edwin {
        int LEFT_TOP = 7;
        int LEFT_BOTTOM = 6;

        int RIGHT_TOP = 4;
        int RIGHT_BOTTOM = 3;

        int GEAR_SHIFT = 0;
    }
}

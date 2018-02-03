/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1987.robot;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
    public static double wheelDiameter = 4.0;
    public static double driveWheelbaseWidth = 25.5 / 12; //feets
    public static double period = .05;
    public static double maxVelocity = 5.0;
    public static double maxAcceleration = 3.0;
    public static double maxJerk = 60.0;
    public static double drivePathP = 1.2;
    public static double drivePathI = 0.0;
    public static double drivePathD = 2.5;
    public static double drivePathV = 1 / maxVelocity;
    public static double drivePathA = 0.9;
    
    public static int ctreMagEncoderTicksPerRevolution = 4096;
    public static int elevatorID = 5;	//turrret id on comp bot (purple)
    public static int leftMasterID = 1;
    public static int leftSlaveID = 3;
    public static int rightMasterID = 2;
    public static int rightSlaveID = 4;
    
    //Claw
    public static int clawMasterID = 4;	//these ids need to be changed
	public static int clawSlaveID = 3;
	public static int clawExtendPCMID = 0;
	public static int clawRetractPCMID = 1;
	public static int farCubeSensorDIOID = 0;
	public static int closeCubeSensorDIOID = 1;
    
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}

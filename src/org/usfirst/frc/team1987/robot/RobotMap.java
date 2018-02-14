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
	
	public static int pcmDrive = 1;
	public static int pcmOther = 0;
	
	//Drive 
	public static int leftMasterID = 1;
	public static int leftSlave1ID = 2;
	public static int leftSlave2ID = 3;
	public static int rightMasterID = 4;	//was 4
	public static int rightSlave1ID = 5;
	public static int rightSlave2ID = 6;		
	
	public static int drivePIDIDX = 0;
	public static int defaultTimeout = 10;
	
	public static double wheelDiameter = 4.0;		//change
	public static double driveWheelbaseWidth = 3.0;	//change
	public static double period = 0.05;
	public static double maxVelocity = 5.0;
	public static double maxAcceration = 3.0;
	public static double maxJerk = 60.0;
	public static double minimumTrajectoryPercentage = 0.55;
	public static double drivePathP = 1.2;
	public static double drivePathI = 0.0;
	public static double drivePathD = 2.5;
	public static double drivePathV = 1 / maxVelocity;
	public static double drivePathA = 0.9;
	public static double ticksPerRotation = 4096.0;
	
//	public static int pidgeyID = 30;	//change this later
//	public static int ahrsID = 58;	//change this later

	// Claw
	public static int clawMaster = 8;
	public static int clawSlave = 9;
	public static int clawFingersOpen = 0;
	public static int clawFingersClosed = 1;
	public static int clawWristUp = 2;
	public static int clawWristDown = 3;
	public static int clawNearCubeSensor = 3; // May need to swap with has cube
	public static int clawHasCubeSensor = 5;
		
	//Xbox - Driver 
	public static int driverID = 0;
	
	//Elevator 
	public static int elevatorID = 7;	///was 7
	public static int ticksPerInch = 554;
	public static int elevatorHomeID = 2;
	public static double winchDiameter = 2.125;
}

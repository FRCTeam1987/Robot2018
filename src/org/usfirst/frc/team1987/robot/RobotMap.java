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
	public static int shifterHigh = 0;
	public static int shifterLow = 1;
	public static int drivePTOEngaged = 2;
	public static int drivePTODisengaged = 3;
	public static int driveDropDownOmniBack = 4;
	public static int driveDropDownOmniFront = 5;
	
	public static double wheelDiameter = 4.625;		//change
	public static double driveWheelbaseWidth = 24.25;	//change
	public static double period = 0.02;
	public static double maxVelocity = 2.7;
	public static double maxAcceration = 1.1725;	//STARTED AT 1.2
	public static double maxJerk = 60.0;
	public static double minimumTrajectoryPercentage = 0.55;
	public static double drivePathP = 1.2;
	public static double drivePathI = 0.0;
	public static double drivePathD = 2.5;
	public static double drivePathV = 1 / maxVelocity;
	public static double drivePathA = 0.9;
	public static double ticksPerRotation = 4096.0;
	
	//Drive PIDs (fix these numbers)
	public static double drivePivotHighP = 0.3;
	public static double drivePivotHighI = 0.0;
	public static double drivePivotHighD = 0.0;
	public static double drivePivotLowP = 0.3;
	public static double drivePivotLowI = 0.0;
	public static double drivePivotLowD = 0.0;
	
	public static double driveStraightHighP = 0.0;
	public static double driveStraightHighI = 0.0;
	public static double driveStraightHighD = 0.0;
	public static double driveStraightLowP = 0.0;
	public static double driveStraightLowI = 0.0;
	public static double driveStraightLowD = 0.0;

	// Claw
	public static int clawRight = 8;
	public static int clawLeft = 9;
	public static int clawFingersOpen = 0;
	public static int clawFingersClosed = 1;
	public static int clawWristUp = 2;
	public static int clawWristDown = 3;
	public static int clawCubeProx = 5;
	public static int clawLeftLimitSwitch = 3;
	public static int clawRightLimitSwitch = 4;
	public static double debounceTime = 0.15;
	public static double strongEject = 0.9;
	public static double weakEject = 0.6;
		
	//Xbox
	public static int driverID = 0;
	public static int coDriverID = 1;
	
	//Driver buttons
	public static int collectWideButton = 3;		//x
	public static int ejectCubeButton = 4;			//y
	public static int toggleShifterButton = 9;		//left stick press down
	public static int goToScaleHeightButton = 1;	//a
	public static int goToHomeButton = 2;			//b
	public static int toggleDropDownOmniFrontButton = 6;	//right bumper
	public static int toggleDropDownOmniBackButton = 5;		//left bumper
	
	//Co-driver buttons
//	public static int disownedScaleButton = ;
//	public static int neutralScaleButton = ;
//	public static int ownedScaleButton = ;
//	public static int disownedWorstButton = ;
	public static int wristToggleButton = 8; 				//start
	public static int stopCollectButton = 2;				//b
	public static int elevatorTopCubePyramidButton = 4;		//y
	public static int elevatorMiddleCubePyramidButton = 3;	//x
	public static int elevatorFloorCubePyramidButton = 1;	//a
	public static int adjustElevatorUpButton = 6;			//right bumper
	public static int adjustElevatorDownButton = 5;			//left bumper
	public static int setStrongEjectButton = 10;			//right stick press down
	public static int setWeakEjectButton = 9;				//left stick press down
	
	//Elevator 
	public static int elevatorID = 7;	///was 7
	public static int elevatorRatchet = 4;
	public static int ticksPerInch = 554;
	public static int elevatorHomeID = 2;
	public static double winchDiameter = 2.2;
	public static int disownedScaleHeight = 28;
	public static int neutralScaleHeight = 23;
	public static int ownedScaleHeight = 15;
	public static double rungHeight = 26.4;
	public static double climbHeight = 55555.0; //find out climb height
	public static double disownedWorstScaleHeight = 30.0;
	public static double elevatorTopCubePyramidHeight = 10.5;
	public static double elevatorMiddleCubePyramidHeight = 4.75;	//5.25
	public static double elevatorHoldCubeHeight = 2.0;
	public static double elevatorFloorCubePyramidHeight = 0;
	public static int elevatorToleranceInTicks = 500;
}

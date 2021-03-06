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
	public static int platforms = 5;		//CHANGE THISSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
	
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
	
	public static double driveStraightHighP = 1.0;
	public static double driveStraightHighI = 0.0;
	public static double driveStraightHighD = 0.0;
	public static double driveStraightLowP = 1.0;
	public static double driveStraightLowI = 0.0;
	public static double driveStraightLowD = 0.0;
	
	public static double drivePathLowKP = 1.2;
	public static double drivePathLowKD = 0.0;	//check this
	public static double drivePathLowKI = 0.0;
	public static double drivePathLowDT = RobotMap.period;
	public static double drivePathLowGP = 0.04;
	public static double drivePathLowGD = 0.0;
	public static double drivePathLowMaxVelocity = 2.0;
	public static double drivePathLowKV = 1.0 / drivePathLowMaxVelocity;
	public static double drivePathLowMaxAcceleration = 2.0;
	public static double drivePathLowKA = 0.0;
	public static double drivePathLowMaxJerk = 7.62;
	
	public static double drivePathStraightKP = 0.8;
	public static double drivePathStraightKD = 0.0;
	public static double drivePathStraightKI = 0.0;
	public static double drivePathStraightDT = RobotMap.period;
	public static double drivePathStraightGP = 0.039;
	public static double drivePathStraightGD = 0.0;
	public static double drivePathStraightMaxVelocity = 3.8;		//3.4
	public static double drivePathStraightKV = 1.0 / drivePathStraightMaxVelocity;
	public static double drivePathStraightMaxAcceleration = 1.8;	//1.4
	public static double drivePathStraightKA = 0.0;
	public static double drivePathStraightMaxJerk = 7.62;
	
	public static double drivePathTurnsKP = 0.9;
	public static double drivePathTurnsKD = 0.4;
	public static double drivePathTurnsKI = 0.0;
	public static double drivePathTurnsDT = RobotMap.period;
	public static double drivePathTurnsGP = 0.039;
	public static double drivePathTurnsGD = 0.0;
	public static double drivePathTurnsMaxVelocity = 3.4;
	public static double drivePathTurnsKV = 1.0 / drivePathTurnsMaxVelocity;
	public static double drivePathTurnsMaxAcceleration = 1.2;
	public static double drivePathTurnsKA = 0.0;
	public static double drivePathTurnsMaxJerk = 7.62;


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
	public static double strongEject = 0.9;		//was 0.9
	public static double weakEject = 0.7;
		
	//Xbox / joysticks
	public static int driverID = 0;
	public static int coDriverID = 1;
	public static int coDriverBoxID = 2;
	
	//Driver buttons
	public static int collectWideButton = 3;		//x
	public static int ejectCubeButton = 4;			//y
	public static int toggleShifterButton = 9;		//left stick press down
	public static int goToScaleHeightButton = 1;	//a
	public static int goToHomeButton = 2;			//b
	public static int toggleDropDownOmniFrontButton = 6;	//right bumper
	public static int toggleDropDownOmniBackButton = 5;		//left bumper
	public static int myClimbButton = 8;			//start
	public static int prepClimbButton = 7;			//back
	
	//Co-driver buttons
//	public static int disownedScaleButton = ;
//	public static int neutralScaleButton = ;
//	public static int ownedScaleButton = ;
//	public static int disownedWorstButton = ;
	public static int wristToggleButton = 8; 				//start
	public static int portalCollectButton = 7;
	public static int stopCollectButton = 2;				//b
	public static int elevatorTopCubePyramidButton = 4;		//y
	public static int elevatorMiddleCubePyramidButton = 3;	//x
	public static int elevatorFloorCubePyramidButton = 1;	//a
	public static int adjustElevatorUpButton = 6;			//right bumper
	public static int adjustElevatorDownButton = 5;			//left bumper
//	public static int setStrongEjectButton = 10;			//right stick press down
//	public static int setWeakEjectButton = 9;				//left stick press down
	public static int toggleEjectSpeedButton = 9;
	
	
	//Co-Driver Box Buttons
	public static int wristSwitch = 13;
	public static int climb = 17;
	public static int stopCollect = 11;
	public static int elevatorTopPyramidCube = 5;
	public static int elevatorMiddlePyramidCube = 18;
	public static int elevatorBottomPyramidCube = 20;
	public static int openFingers = 19;
	public static int spitSpeed = 12;
	public static int elevatorAdjustUp = 14;
	public static int elevatorAdjustDown = 8;
	public static int elevatorMaxHeight = 10;
	public static int disownedScale = 16;
	public static int neutralScale = 15;
	public static int ownedScale = 9;
	
	//Elevator 
	public static int elevatorID = 7;	
	public static int elevatorRatchet = 4;
	public static int ticksPerInch = 554;
	public static int elevatorHomeID = 2;
	public static double winchDiameter = 2.2;
	public static int disownedScaleHeight = 29;	//was 28
	public static int neutralScaleHeight = 25;	//was 23
	public static int ownedScaleHeight = 17;	//was 15
	public static double rungHeight = 26.75;
	public static double climbHeight = 55555.0; 
	public static double disownedWorstScaleHeight = 32.0;	//was 31
	public static double elevatorTopCubePyramidHeight = 10.5;
	public static double elevatorMiddleCubePyramidHeight = 5.75;	//was 4.75 // also was 6.75
	public static double elevatorHoldCubeHeight = 4.0;
	public static double elevatorFloorCubePyramidHeight = 0;
	public static int elevatorToleranceInTicks = 500;
}

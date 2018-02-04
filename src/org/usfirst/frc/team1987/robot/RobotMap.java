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
	
	//Drive 
	public static int leftMasterID = 1;
	public static int leftSlave1ID = 2;
	public static int leftSlave2ID = 3;
	public static int rightMasterID = 4;
	public static int rightSlave1ID = 5;
	public static int rightSlave2ID = 6;	
	
	//Xbox - Driver 
	public static int xboxDriverID = 0;
}

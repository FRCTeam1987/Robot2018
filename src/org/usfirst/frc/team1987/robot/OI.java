/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1987.robot;

import org.usfirst.frc.team1987.robot.commands.drive.DriveStraightForDistance;
import org.usfirst.frc.team1987.robot.commands.elevator.GoToAbsolutePosition;
import org.usfirst.frc.team1987.robot.commands.elevator.GoToRelativePosition;
import org.usfirst.frc.team1987.robot.commands.elevator.SetZeroHome;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private final XboxController xbox = new XboxController(0);
	
	public OI()
	{
		SmartDashboard.putData("Drive Straight 6", new DriveStraightForDistance(6));
		SmartDashboard.putData("Elevator relative: 8", new GoToRelativePosition(8));
		SmartDashboard.putData("Set Home", new SetZeroHome());
//		SmartDashboard.putData("Elevator absolute: 5", new GoToAbsolutePosition(5));
	}
	
	public XboxController getXbox()
	{
		return xbox;
	}
	
}

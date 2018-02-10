/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1987.robot;

import org.usfirst.frc.team1987.robot.commands.DisableCompressor;
import org.usfirst.frc.team1987.robot.commands.EnableCompressor;
import org.usfirst.frc.team1987.robot.commands.drive.DriveStraightForDistance;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private final XboxController driver;
	
	public OI()
	{
		driver = new XboxController(RobotMap.driverID);
		
		SmartDashboard.putData("Drive straight 10", new DriveStraightForDistance(10));
		SmartDashboard.putData("Drive straight -10", new DriveStraightForDistance(-10));
		SmartDashboard.putData("Enable compressor", new EnableCompressor());
		SmartDashboard.putData("Disable compressor", new DisableCompressor());
	}
	
	public XboxController getDriver() {
		return driver;
	}
	
}

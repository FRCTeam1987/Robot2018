/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1987.robot;

import org.usfirst.frc.team1987.robot.commands.DisableCompressor;
import org.usfirst.frc.team1987.robot.commands.EnableCompressor;
import org.usfirst.frc.team1987.robot.commands.claw.CloseClaw;
import org.usfirst.frc.team1987.robot.commands.claw.CollectCubeNarrow;
import org.usfirst.frc.team1987.robot.commands.claw.CollectCubeWide;
import org.usfirst.frc.team1987.robot.commands.claw.EjectCube;
import org.usfirst.frc.team1987.robot.commands.claw.OpenClaw;
import org.usfirst.frc.team1987.robot.commands.claw.SetClawWheelSpeed;
import org.usfirst.frc.team1987.robot.commands.drive.DriveStraightForDistance;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
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
		SmartDashboard.putData("Collect Cube Narrow", new CollectCubeNarrow());
		SmartDashboard.putData("Collect Cube Wide", new CollectCubeWide());
		SmartDashboard.putData("Eject Cube", new EjectCube());
		SmartDashboard.putData("Set Claw speed .6", new SetClawWheelSpeed(.6));
		SmartDashboard.putData("Open Claw", new OpenClaw());
		SmartDashboard.putData("Close claw", new CloseClaw());
		
		
		Button eject = new JoystickButton(driver, 2);			//b
		Button closeClaw = new JoystickButton(driver, 5);		//left bumper
		Button openClaw = new JoystickButton(driver, 6);		//right bumper
		Button collectWide = new JoystickButton(driver, 1);		//a
		Button collectNarrow = new JoystickButton(driver, 3);	//x
		
		eject.whenPressed(new EjectCube());
		closeClaw.whenPressed(new CloseClaw());
		openClaw.whenPressed(new OpenClaw());
		collectWide.whenPressed(new CollectCubeWide());
		collectNarrow.whenPressed(new CollectCubeNarrow());

		
		
	}
	
	public XboxController getDriver() {
		return driver;
	}
	
}

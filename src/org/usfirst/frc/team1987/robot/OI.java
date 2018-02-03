/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1987.robot;

import org.usfirst.frc.team1987.robot.commands.claw.CollectCubeNarrow;
import org.usfirst.frc.team1987.robot.commands.claw.CollectCubeWide;
import org.usfirst.frc.team1987.robot.commands.claw.EjectCube;
//import org.usfirst.frc.team1987.robot.commands.CollectCubeNarrow;
//import org.usfirst.frc.team1987.robot.commands.CollectCubeWide;
//import org.usfirst.frc.team1987.robot.commands.EjectCube;
//import org.usfirst.frc.team1987.robot.commands.SetClawActuator;
//import org.usfirst.frc.team1987.robot.commands.SetClawWheelSpeed;
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
		SmartDashboard.putData("Elevator relative: 5", new GoToRelativePosition(5));
		SmartDashboard.putData("Elevator relative: 8", new GoToRelativePosition(8));
		SmartDashboard.putData("Set Home", new SetZeroHome());
//		SmartDashboard.putData("Elevator absolute: 5", new GoToAbsolutePosition(5));
		
		// drive
//		SmartDashboard.putData("Drive Straight 6", new DriveStraightForDistance(6));
		
		// claw
//		SmartDashboard.putData("Set wheel motor to 90 %", new SetClawWheelSpeed(.9));
//		SmartDashboard.putData("Set wheel motor to -50 %", new SetClawWheelSpeed(-0.5));
//		SmartDashboard.putData("Set wheel motor to 0 %", new SetClawWheelSpeed(0.0));
//		SmartDashboard.putData("Set cylinders out", new SetClawActuator(true));
//		SmartDashboard.putData("Set cylinders in", new SetClawActuator(false));
//		
		SmartDashboard.putData("Collect cube wide", new CollectCubeWide());
		SmartDashboard.putData("Collect cube narrow", new CollectCubeNarrow());
//		SmartDashboard.putData("spit", new EjectCube());
	}
	
	public XboxController getXbox()
	{
		return xbox;
	}
	
}

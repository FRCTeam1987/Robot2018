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
import org.usfirst.frc.team1987.robot.commands.claw.CollectCubeWide;
import org.usfirst.frc.team1987.robot.commands.claw.EjectCube;
import org.usfirst.frc.team1987.robot.commands.claw.OpenClaw;
import org.usfirst.frc.team1987.robot.commands.claw.SetClawWheelSpeed;
import org.usfirst.frc.team1987.robot.commands.claw.StopCollect;
import org.usfirst.frc.team1987.robot.commands.claw.AdjustCubeInClaw;
import org.usfirst.frc.team1987.robot.commands.claw.WristDeploy;
import org.usfirst.frc.team1987.robot.commands.claw.WristStow;
import org.usfirst.frc.team1987.robot.commands.drive.DriveStraightForDistance;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team1987.robot.commands.drive.ToggleShifter;
import org.usfirst.frc.team1987.robot.commands.elevator.AdjustElevatorHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeight;

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
	private final Button eject;
	private final Button startCollect;
	private final Button stopCollect;
	private final Button collectWide;
	private final Button wristDeploy;
	private final Button wristStow;
	private final Button toggleShifter;

	
	public OI()
	{
		driver = new XboxController(RobotMap.driverID);
		
//		SmartDashboard.putData("Drive straight 10", new DriveStraightForDistance(10));
//		SmartDashboard.putData("Drive straight -10", new DriveStraightForDistance(-10));
//		SmartDashboard.putData("Enable compressor", new EnableCompressor());
//		SmartDashboard.putData("Disable compressor", new DisableCompressor());
//		SmartDashboard.putData("Collect Cube Narrow", new CollectCubeNarrow());
//		SmartDashboard.putData("Collect Cube Wide", new CollectCubeWide());
//		SmartDashboard.putData("Eject Cube", new EjectCube());
//		SmartDashboard.putData("Set Claw speed .6", new SetClawWheelSpeed(.6));
//		SmartDashboard.putData("Wait for has cube", new AdjustCubeInClaw(5.0, 0.6, 0.6));
//		SmartDashboard.putData("Open Claw", new OpenClaw());
//		SmartDashboard.putData("Close claw", new CloseClaw());
//		SmartDashboard.putData("Wrist Stow", new WristStow());
//		SmartDashboard.putData("Wrist Deploy", new WristDeploy());
//		SmartDashboard.putData("Shift high", new ShiftHigh());
//		SmartDashboard.putData("Shift low", new ShiftLow());
		SmartDashboard.putData("Adjust elevator +3", new AdjustElevatorHeight(3));
		SmartDashboard.putData("Adjust elevator -3", new AdjustElevatorHeight(-3));
		SmartDashboard.putData("Set max: 30.875", new SetElevatorHeight(30.850));
		SmartDashboard.putData("Set home: 0", new SetElevatorHeight(0));
		SmartDashboard.putData("Set 24.72", new SetElevatorHeight(24.72));
		SmartDashboard.putData("Set 10", new SetElevatorHeight(10));
		SmartDashboard.putData("Set 20", new SetElevatorHeight(20));
//		SmartDashboard.putData("Set to rung height", new SetElevatorHeight(23.7));
//		SmartDashboard.putData("Toggle Shitter", new ToggleShifter());
		
		
		
		eject = new JoystickButton(driver, 4);			//y
		startCollect = new JoystickButton(driver, 1);	//a
		stopCollect = new JoystickButton(driver, 2);		//b
		wristStow = new JoystickButton(driver, 5);		//left bumper
		wristDeploy = new JoystickButton(driver, 6);		//right bumper
		collectWide = new JoystickButton(driver, 3);		//x
//		elevatorMax = new JoystickButton(driver, );
		toggleShifter = new JoystickButton(driver, 10);
		
		eject.whenPressed(new EjectCube());
		startCollect.whenPressed(new SetClawWheelSpeed(-0.7));
		stopCollect.whenPressed(new StopCollect());
		wristStow.whenPressed(new WristStow());
		wristDeploy.whenPressed(new WristDeploy());
		collectWide.whenPressed(new CollectCubeWide());
		toggleShifter.whenPressed(new ToggleShifter());
		
		
		
	}
	
	public XboxController getDriver() {
		return driver;
	}
	
}

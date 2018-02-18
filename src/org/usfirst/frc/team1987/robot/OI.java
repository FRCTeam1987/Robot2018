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
import org.usfirst.frc.team1987.robot.commands.claw.TeleCollectCubeWide;
import org.usfirst.frc.team1987.robot.commands.claw.EjectCube;
import org.usfirst.frc.team1987.robot.commands.claw.OpenClaw;
import org.usfirst.frc.team1987.robot.commands.claw.SetClawWheelSpeed;
import org.usfirst.frc.team1987.robot.commands.claw.StopCollect;
import org.usfirst.frc.team1987.robot.commands.claw.ToggleWrist;
import org.usfirst.frc.team1987.robot.commands.claw.AdjustCubeInClaw;
import org.usfirst.frc.team1987.robot.commands.claw.WristDeploy;
import org.usfirst.frc.team1987.robot.commands.claw.WristStow;
import org.usfirst.frc.team1987.robot.commands.drive.DriveStraightForDistance;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team1987.robot.commands.drive.ToggleShifter;
import org.usfirst.frc.team1987.robot.commands.elevator.AdjustElevatorHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeight;

import edu.wpi.first.wpilibj.Joystick;
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
	private final XboxController coDriver;
	private final Button eject;
//	private final Button startCollect;
	private final Button stopCollect;
	private final Button collectWide;
//	private final Button wristDeploy;
//	private final Button wristStow;
	private final Button toggleWrist;
	private final Button toggleShifter;


	
	public OI()
	{
		driver = new XboxController(RobotMap.driverID);
		coDriver = new XboxController(RobotMap.coDriverID);
		
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
		SmartDashboard.putData("Set max: 30", new SetElevatorHeight(30));
		SmartDashboard.putData("Set 2", new SetElevatorHeight(2));
		SmartDashboard.putData("Set home: 0", new SetElevatorHeight(0));
		SmartDashboard.putData("Set 24.72", new SetElevatorHeight(24.72));
		SmartDashboard.putData("Set 10", new SetElevatorHeight(10));
		SmartDashboard.putData("Set 20", new SetElevatorHeight(20));
		SmartDashboard.putData("Set to rung height", new SetElevatorHeight(23.7));
		SmartDashboard.putData("Toggle Shifter", new ToggleShifter());
		SmartDashboard.putData("elevator second cube pyramid", new SetElevatorHeight(5.25));
		SmartDashboard.putData("elevator third cube pyramid", new SetElevatorHeight(10.5));
		
		
		eject = new JoystickButton(driver, RobotMap.ejectCubeButton);			//y
//		startCollect = new JoystickButton(driver, 3);	//a
		stopCollect = new JoystickButton(driver, RobotMap.stopCollectButton);		//b
//		wristStow = new JoystickButton(driver, 5);		//left bumper
//		wristDeploy = new JoystickButton(driver, 6);		//right bumper
		toggleWrist = new JoystickButton(driver, RobotMap.toggleWristButton);
		collectWide = new JoystickButton(driver, RobotMap.collectWideButton);		//x
		toggleShifter = new JoystickButton(driver, RobotMap.toggleShifterButton);
		
		eject.whenPressed(new EjectCube());
//		startCollect.whenPressed(new SetClawWheelSpeed(-0.7));
		stopCollect.whenPressed(new StopCollect());
//		wristStow.whenPressed(new WristStow());
//		wristDeploy.whenPressed(new WristDeploy());
		toggleWrist.whenPressed(new ToggleWrist());
		collectWide.whenPressed(new TeleCollectCubeWide());
		toggleShifter.whenPressed(new ToggleShifter());
		
		//D-pad code (experimental)
		
		if (driver.getPOV() == 0) {
			new SetElevatorHeight(30.85);
		} 
		else if (driver.getPOV() == 90) {
			new SetElevatorHeight(10);
		}
		else if (driver.getPOV() == 180) {
			new SetElevatorHeight(0);
		}
		else if (driver.getPOV() == 270) {
			new SetElevatorHeight(20);
		}
			
	
	
		
		
	}
	
	public XboxController getDriver() {
		return driver;
	}
	
	public XboxController getCoDriver() {
		return coDriver;
	}
	
}

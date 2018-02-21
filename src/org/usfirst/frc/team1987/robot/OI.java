/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1987.robot;

import org.usfirst.frc.team1987.robot.commands.SetPotentialCollectorHeight;
import org.usfirst.frc.team1987.robot.commands.SetScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.claw.AutoCollectCubeWide;
import org.usfirst.frc.team1987.robot.commands.claw.CloseClaw;
import org.usfirst.frc.team1987.robot.commands.claw.EjectCube;
import org.usfirst.frc.team1987.robot.commands.claw.OpenClaw;
import org.usfirst.frc.team1987.robot.commands.claw.SetClawWheelSpeed;
import org.usfirst.frc.team1987.robot.commands.claw.StopCollect;
import org.usfirst.frc.team1987.robot.commands.claw.TeleCollectCubeWide;
import org.usfirst.frc.team1987.robot.commands.claw.ToggleWrist;
import org.usfirst.frc.team1987.robot.commands.claw.WristDeploy;
import org.usfirst.frc.team1987.robot.commands.claw.WristStow;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team1987.robot.commands.drive.ToggleDropDownOmniBack;
import org.usfirst.frc.team1987.robot.commands.drive.ToggleDropDownOmniFront;
import org.usfirst.frc.team1987.robot.commands.drive.TogglePto;
import org.usfirst.frc.team1987.robot.commands.drive.ToggleShifter;
import org.usfirst.frc.team1987.robot.commands.elevator.AdjustElevatorHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.GoToScaleHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.ToggleRatchet;

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
	private final Button stopCollect;
	private final Button collectWide;
	private final Button adjustUp;
	private final Button adjustDown;
	private final Button wristToggle;
	private final Button toggleShifter;
	private final Button goToScaleHeight;
//	private final Button disownedButton;
//	private final Button neutralButton;
//	private final Button ownedButton;
//	private final Button disownedWorstButton;
	private final Button goToHome;
	private final Button elevatorMiddleCubeHeight;
	private final Button elevatorTopCubeHeight;
	private final Button elevatorFloorCubeHeight;
	
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
//		SmartDashboard.putData("Adjust elevator +5", new AdjustElevatorHeight(5));
//		SmartDashboard.putData("Adjust elevator -5", new AdjustElevatorHeight(-5));
//		SmartDashboard.putData("Set max: 30", new SetElevatorHeight(30));
//		SmartDashboard.putData("Set 2", new SetElevatorHeight(2));
//		SmartDashboard.putData("Set home: 0", new SetElevatorHeight(0));
//		SmartDashboard.putData("Set 24.72", new SetElevatorHeight(24.72));
//		SmartDashboard.putData("Set 10", new SetElevatorHeight(10));
//		SmartDashboard.putData("Set 20", new SetElevatorHeight(20));
//		SmartDashboard.putData("Set to rung height", new SetElevatorHeight(23.7));
//		SmartDashboard.putData("Toggle Shifter", new ToggleShifter());
//		SmartDashboard.putData("elevator second cube pyramid", new SetElevatorHeight(5.25));
//		SmartDashboard.putData("elevator third cube pyramid", new SetElevatorHeight(10.5));
//		SmartDashboard.putData("Scale ownership: disowned w/ cube", new SetScaleOwnership(ScaleOwnership.DISOWNED_WORST));
//		SmartDashboard.putData("Scale ownership: disowned", new SetScaleOwnership(ScaleOwnership.DISOWNED));
//		SmartDashboard.putData("Scale ownership: neutral", new SetScaleOwnership(ScaleOwnership.NEUTRAL));
//		SmartDashboard.putData("Scale ownership: owned", new SetScaleOwnership(ScaleOwnership.OWNED));
		SmartDashboard.putData("Toggle PTO", new TogglePto());
		SmartDashboard.putData("Toggle Omni Back", new ToggleDropDownOmniBack());
		SmartDashboard.putData("Toggle Omni Front", new ToggleDropDownOmniFront());
		SmartDashboard.putData("Toggle Ratchet", new ToggleRatchet());
		
		eject = new JoystickButton(driver, RobotMap.ejectCubeButton);			//y
		collectWide = new JoystickButton(driver, RobotMap.collectWideButton);	
		toggleShifter = new JoystickButton(driver, RobotMap.toggleShifterButton);
		goToScaleHeight = new JoystickButton(driver, RobotMap.goToScaleHeightButton);
		goToHome = new JoystickButton(driver, RobotMap.goToHomeButton);
		
//		disownedButton = new JoystickButton(coDriver, RobotMap.disownedScaleButton);
//		neutralButton = new JoystickButton(coDriver, RobotMap.neutralScaleButton);
//		ownedButton = new JoystickButton(coDriver, RobotMap.ownedScaleButton);
//		disownedWorstButton = new JoystickButton(coDriver, RobotMap.disownedWorstButton);
		stopCollect = new JoystickButton(coDriver, RobotMap.stopCollectButton);		//b
		elevatorMiddleCubeHeight = new JoystickButton(coDriver, RobotMap.elevatorMiddleCubePyramidButton);
		elevatorTopCubeHeight = new JoystickButton(coDriver, RobotMap.elevatorTopCubePyramidButton);
		elevatorFloorCubeHeight = new JoystickButton(coDriver, RobotMap.elevatorFloorCubePyramidButton);
		wristToggle = new JoystickButton(coDriver, RobotMap.wristToggleButton);		
		adjustUp = new JoystickButton(coDriver, RobotMap.adjustElevatorUpButton);
		adjustDown = new JoystickButton(coDriver, RobotMap.adjustElevatorDownButton);
		
		//Driver
		eject.whenPressed(new EjectCube());
		collectWide.whenPressed(new TeleCollectCubeWide());
		toggleShifter.whenPressed(new ToggleShifter());
		goToScaleHeight.whenPressed(new GoToScaleHeight());
		goToHome.whenPressed(new SetElevatorHeight(0));		
		
		//Co-driver
//		disownedButton.whenPressed(new SetScaleOwnership(ScaleOwnership.DISOWNED));
//		neutralButton.whenPressed(new SetScaleOwnership(ScaleOwnership.NEUTRAL));
//		ownedButton.whenPressed(new SetScaleOwnership(ScaleOwnership.OWNED));
//		disownedWorstButton.whenPressed(new SetScaleOwnership(ScaleOwnership.DISOWNED_WORST));
		stopCollect.whenPressed(new StopCollect());
		elevatorMiddleCubeHeight.whenPressed(new SetPotentialCollectorHeight(CollectorHeight.MIDDLE));
		elevatorTopCubeHeight.whenPressed(new SetPotentialCollectorHeight(CollectorHeight.TOP));
		elevatorFloorCubeHeight.whenPressed(new SetPotentialCollectorHeight(CollectorHeight.FLOOR));
		wristToggle.whenPressed(new ToggleWrist());
		adjustUp.whenPressed(new AdjustElevatorHeight(5));
		adjustDown.whenPressed(new AdjustElevatorHeight(-5));
		
		//D-pad code (experimental)
		
		if (driver.getPOV() == 0) {
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

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1987.robot;

import org.usfirst.frc.team1987.robot.commands.Climb;
import org.usfirst.frc.team1987.robot.commands.DropPlatforms;
import org.usfirst.frc.team1987.robot.commands.MyClimb;
import org.usfirst.frc.team1987.robot.commands.PrepClimb;
//import org.usfirst.frc.team1987.robot.commands.Climb;
//import org.usfirst.frc.team1987.robot.commands.DisableCompressor;
//import org.usfirst.frc.team1987.robot.commands.EnableCompressor;
import org.usfirst.frc.team1987.robot.commands.SetPotentialCollectorHeight;
import org.usfirst.frc.team1987.robot.commands.SetScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.auto.LeftSwitchSwoop;
import org.usfirst.frc.team1987.robot.commands.auto.MiddleToRightSwitch;
import org.usfirst.frc.team1987.robot.commands.auto.MiddleToRightSwitchLeftSwitch;
import org.usfirst.frc.team1987.robot.commands.auto.MiddleToRightSwitchSwoop;
import org.usfirst.frc.team1987.robot.commands.auto.RightSwitchSwoop;
import org.usfirst.frc.team1987.robot.commands.auto.RightToLeftScaleExtraSketch;
import org.usfirst.frc.team1987.robot.commands.auto.RightToLeftSwitch;
import org.usfirst.frc.team1987.robot.commands.auto.RightToRightSwitch;
import org.usfirst.frc.team1987.robot.commands.claw.AutoCollectCubeWide;
import org.usfirst.frc.team1987.robot.commands.claw.CloseClaw;
import org.usfirst.frc.team1987.robot.commands.claw.OpenClaw;
//import org.usfirst.frc.team1987.robot.commands.claw.CloseClaw;
//import org.usfirst.frc.team1987.robot.commands.claw.EjectAndJiggle;
//import org.usfirst.frc.team1987.robot.commands.claw.EjectCube;
//import org.usfirst.frc.team1987.robot.commands.claw.OpenClaw;
//import org.usfirst.frc.team1987.robot.commands.claw.SetClawWheelSpeed;
import org.usfirst.frc.team1987.robot.commands.claw.SetEjectStrength;
import org.usfirst.frc.team1987.robot.commands.claw.StopCollect;
import org.usfirst.frc.team1987.robot.commands.claw.TeleCollectCubeWide;
import org.usfirst.frc.team1987.robot.commands.claw.TeleCollectStowed;
import org.usfirst.frc.team1987.robot.commands.claw.TeleopEjectCube;
import org.usfirst.frc.team1987.robot.commands.claw.ToggleEjectSpeed;
import org.usfirst.frc.team1987.robot.commands.claw.ToggleWrist;
import org.usfirst.frc.team1987.robot.commands.claw.WristDeploy;
import org.usfirst.frc.team1987.robot.commands.claw.WristStow;
import org.usfirst.frc.team1987.robot.commands.drive.DisengagePto;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
//import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
//import org.usfirst.frc.team1987.robot.commands.drive.PIDDrivePivot;
//import org.usfirst.frc.team1987.robot.commands.drive.ShiftHigh;
//import org.usfirst.frc.team1987.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team1987.robot.commands.drive.ToggleDropDownOmniBack;
import org.usfirst.frc.team1987.robot.commands.drive.ToggleDropDownOmniFront;
//import org.usfirst.frc.team1987.robot.commands.drive.TogglePto;
import org.usfirst.frc.team1987.robot.commands.drive.ToggleShifter;
import org.usfirst.frc.team1987.robot.commands.elevator.AdjustElevatorHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.ElevatorManual;
import org.usfirst.frc.team1987.robot.commands.elevator.GoToCollectorHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.GoToScaleHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team1987.util.AutoPaths;
//import org.usfirst.frc.team1987.robot.commands.elevator.ToggleRatchet;
import org.usfirst.frc.team1987.util.XboxDPad;

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
	private final Joystick buttonBox;
	private final Button wristSwitch;
	private final Button climb;
	private final Button stopCollect;
	private final Button elevatorTopPyramidCube;
	private final Button elevatorMiddlePyramidCube;
	private final Button elevatorBottomPyramidCube;
	private final Button openFingers;
	private final Button spitSpeed;
	private final Button elevatorAdjustUp;
	private final Button elevatorAdjustDown;
	private final Button elevatorMaxHeight;
	private final Button disownedScale;
	private final Button neutralScale;
	private final Button ownedScale;
	private final Button eject;
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
	private final Button toggleDropDownOmniBack;
	private final Button toggleDropDownOmniFront;	
	private final Button toggleEjectSpeed;
//	private final Button setStrongEject;
//	private final Button setWeakEject;
	private final Button myClimb;
	private final Button prepClimb;
	private final Button portalCollect;
	private final XboxDPad elevatorManualUp;
	private final XboxDPad elevatorManualDown;
	private final XboxDPad setScaleDisownedWorst;
	private final XboxDPad setScaleDisowned;
	private final XboxDPad setScaleNeutral;
	private final XboxDPad setScaleOwned;
	private final XboxDPad goToCollectHeight;
	private final XboxDPad disengagePto;
	
	public OI()
	{
		driver = new XboxController(RobotMap.driverID);
		coDriver = new XboxController(RobotMap.coDriverID);
		buttonBox = new Joystick(RobotMap.coDriverBoxID);
		
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
//		SmartDashboard.putData("Set max: 29.5", new SetElevatorHeight(29.5));
//		SmartDashboard.putData("Set 35", new SetElevatorHeight(35));
//		SmartDashboard.putData("Set 2", new SetElevatorHeight(2));
//		SmartDashboard.putData("Set home: 0", new SetElevatorHeight(0));
//		SmartDashboard.putData("Set 24.72", new SetElevatorHeight(24.72));
//		SmartDashboard.putData("Set 10", new SetElevatorHeight(10));
//		SmartDashboard.putData("Set 20", new SetElevatorHeight(20));
//		SmartDashboard.putData("Set to rung height", new SetElevatorHeight(RobotMap.rungHeight));
//		SmartDashboard.putData("Climb", new Climb());
//		SmartDashboard.putData("Toggle Shifter", new ToggleShifter());
//		SmartDashboard.putData("elevator second cube pyramid", new SetElevatorHeight(5.25));
//		SmartDashboard.putData("elevator third cube pyramid", new SetElevatorHeight(10.5));
//		SmartDashboard.putData("Scale ownership: disowned w/ cube", new SetScaleOwnership(ScaleOwnership.DISOWNED_WORST));
//		SmartDashboard.putData("Scale ownership: disowned", new SetScaleOwnership(ScaleOwnership.DISOWNED));
//		SmartDashboard.putData("Scale ownership: neutral", new SetScaleOwnership(ScaleOwnership.NEUTRAL));
//		SmartDashboard.putData("Scale ownership: owned", new SetScaleOwnership(ScaleOwnership.OWNED));
//		SmartDashboard.putData("Toggle PTO", new TogglePto());
//		SmartDashboard.putData("Toggle Omni Back", new ToggleDropDownOmniBack());
//		SmartDashboard.putData("Toggle Omni Front", new ToggleDropDownOmniFront());
//		SmartDashboard.putData("Toggle Ratchet", new ToggleRatchet());
//		SmartDashboard.putData("Pivot 45 degrees", new PIDDrivePivot(45.0));
//		SmartDashboard.putData("Drive pivot 0", new DrivePivot(0.0));
//		SmartDashboard.putData("Drive pivot 45", new DrivePivot(45.0));
//		SmartDashboard.putData("Drive pivot 90", new DrivePivot(90.0));
//		SmartDashboard.putData("Drive pivot 180", new DrivePivot(180.0));
//		SmartDashboard.putData("Drive pivot -132", new DrivePivot(-132));
//		SmartDashboard.putData("Drive Pivot 132", new DrivePivot(132));
//		SmartDashboard.putData("EjectAndJiggle", new EjectAndJiggle());
//		SmartDashboard.putData("Drive back a skosh", new DriveAScosh(-15.0));
//		SmartDashboard.putData("drive distance 24", new DriveDistance(24));
//		SmartDashboard.putData("drive distance 12", new DriveDistance(12));
//		SmartDashboard.putData("drive distance 6", new DriveDistance(6));
//		SmartDashboard.putData("drive distance -10", new DriveDistance(-10));
//		SmartDashboard.putData("drive distance 24", new DriveDistance(24));		
//		SmartDashboard.putData("auto collect", new AutoCollectCubeWide());
//		SmartDashboard.putData("toggle shifter", new ToggleShifter());
//		SmartDashboard.putData("drop platforms", new DropPlatforms());
//		SmartDashboard.putData("tele collect", new TeleCollectCubeWide());
//		SmartDashboard.putData("Left Switch Swoop", new LeftSwitchSwoop());
//		SmartDashboard.putData("right switch from middle", new MiddleToRightSwitchSwoop());
//		SmartDashboard.putData("middle to right switch", new MiddleToRightSwitch());
//		SmartDashboard.putData("middle to right switch left switch", new MiddleToRightSwitchLeftSwitch());
//		SmartDashboard.putData("right switch to scale swoop", new Auto());
//		SmartDashboard.putData("super sketch path", new RightToLeftScaleExtraSketch());
//		SmartDashboard.putData("Right to right Switch", new RightToRightSwitch());
//		SmartDashboard.putData("Right switch swoop", new RightSwitchSwoop());
		SmartDashboard.putData("Right to Left Switch", new RightToLeftSwitch());
		
		
		eject = new JoystickButton(driver, RobotMap.ejectCubeButton);			//y
		collectWide = new JoystickButton(driver, RobotMap.collectWideButton);	
		toggleShifter = new JoystickButton(driver, RobotMap.toggleShifterButton);
		goToScaleHeight = new JoystickButton(driver, RobotMap.goToScaleHeightButton);
		goToHome = new JoystickButton(driver, RobotMap.goToHomeButton);
		toggleDropDownOmniFront = new JoystickButton(driver, RobotMap.toggleDropDownOmniFrontButton);
		toggleDropDownOmniBack = new JoystickButton(driver, RobotMap.toggleDropDownOmniBackButton);
		myClimb = new JoystickButton(driver, RobotMap.myClimbButton);
		prepClimb = new JoystickButton(driver, RobotMap.prepClimbButton);
		elevatorManualUp = new XboxDPad(driver, XboxDPad.Direction.Up);
		elevatorManualDown = new XboxDPad(driver, XboxDPad.Direction.Down);
		goToCollectHeight = new XboxDPad(driver, XboxDPad.Direction.Right);
		disengagePto = new XboxDPad(driver, XboxDPad.Direction.Left);
		
//		disownedButton = new JoystickButton(coDriver, RobotMap.disownedScaleButton);
//		neutralButton = new JoystickButton(coDriver, RobotMap.neutralScaleButton);
//		ownedButton = new JoystickButton(coDriver, RobotMap.ownedScaleButton);
//		disownedWorstButton = new JoystickButton(coDriver, RobotMap.disownedWorstButton);
		stopCollect = new JoystickButton(coDriver, RobotMap.stopCollectButton);		//b
		elevatorMiddleCubeHeight = new JoystickButton(coDriver, RobotMap.elevatorMiddleCubePyramidButton);
		elevatorTopCubeHeight = new JoystickButton(coDriver, RobotMap.elevatorTopCubePyramidButton);
		elevatorFloorCubeHeight = new JoystickButton(coDriver, RobotMap.elevatorFloorCubePyramidButton);
		wristToggle = new JoystickButton(coDriver, RobotMap.wristToggleButton);		
		portalCollect = new JoystickButton(coDriver, RobotMap.portalCollectButton);
		adjustUp = new JoystickButton(coDriver, RobotMap.adjustElevatorUpButton);
		adjustDown = new JoystickButton(coDriver, RobotMap.adjustElevatorDownButton);
//		setStrongEject = new JoystickButton(coDriver, RobotMap.setStrongEjectButton);
//		setWeakEject = new JoystickButton(coDriver, RobotMap.setWeakEjectButton);
		toggleEjectSpeed = new JoystickButton(coDriver, RobotMap.toggleEjectSpeedButton);
		setScaleDisowned = new XboxDPad(coDriver, XboxDPad.Direction.Up);
		setScaleNeutral = new XboxDPad(coDriver, XboxDPad.Direction.Right);
		setScaleDisownedWorst = new XboxDPad(coDriver, XboxDPad.Direction.Left);
		setScaleOwned = new XboxDPad(coDriver, XboxDPad.Direction.Down);
		
		wristSwitch = new JoystickButton(buttonBox, RobotMap.wristSwitch);
		climb = new JoystickButton(buttonBox, RobotMap.climb);
		elevatorTopPyramidCube = new JoystickButton(buttonBox, RobotMap.elevatorTopPyramidCube);
		elevatorMiddlePyramidCube = new JoystickButton(buttonBox, RobotMap.elevatorMiddlePyramidCube);
		elevatorBottomPyramidCube = new JoystickButton(buttonBox, RobotMap.elevatorBottomPyramidCube);
		openFingers = new JoystickButton(buttonBox, RobotMap.openFingers);
		spitSpeed = new JoystickButton(buttonBox, RobotMap.spitSpeed);
		elevatorAdjustUp = new JoystickButton(buttonBox, RobotMap.elevatorAdjustUp);
		elevatorAdjustDown = new JoystickButton(buttonBox, RobotMap.elevatorAdjustDown);
		elevatorMaxHeight = new JoystickButton(buttonBox, RobotMap.elevatorMaxHeight);
		disownedScale = new JoystickButton(buttonBox, RobotMap.disownedScale);
		neutralScale = new JoystickButton(buttonBox, RobotMap.neutralScale);
		ownedScale = new JoystickButton(buttonBox, RobotMap.ownedScale);
		
		
		//Driver
		eject.whenPressed(new TeleopEjectCube());
		collectWide.whenPressed(new TeleCollectCubeWide());
		toggleShifter.whenPressed(new ToggleShifter());
		goToScaleHeight.whenPressed(new GoToScaleHeight());
		goToHome.whenPressed(new SetElevatorHeight(0.0));		
		toggleDropDownOmniFront.whenPressed(new ToggleDropDownOmniFront());
		toggleDropDownOmniBack.whenPressed(new ToggleDropDownOmniBack());
		myClimb.whenPressed(new MyClimb());
		prepClimb.whenPressed(new PrepClimb());
		elevatorManualUp.whileHeld(new ElevatorManual(0.4));
		elevatorManualDown.whileHeld(new ElevatorManual(-0.4));
		disengagePto.whenPressed(new DisengagePto());
		
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
		portalCollect.whenPressed(new TeleCollectStowed());
		adjustUp.whenPressed(new AdjustElevatorHeight(5));
		adjustDown.whenPressed(new AdjustElevatorHeight(-5));
//		setStrongEject.whenPressed(new SetEjectStrength(true));
//		setWeakEject.whenPressed(new SetEjectStrength(false));
		toggleEjectSpeed.whenPressed(new ToggleEjectSpeed());
		setScaleDisownedWorst.whenPressed(new SetScaleOwnership(ScaleOwnership.DISOWNED_WORST));
		setScaleDisowned.whenPressed(new SetScaleOwnership(ScaleOwnership.DISOWNED));
		setScaleNeutral.whenPressed(new SetScaleOwnership(ScaleOwnership.NEUTRAL));
		setScaleOwned.whenPressed(new SetScaleOwnership(ScaleOwnership.OWNED));
		goToCollectHeight.whenPressed(new GoToCollectorHeight());
		
		//Co-driver box
//		wristSwitch.whenPressed(new WristDeploy());
//		wristSwitch.whenReleased(new WristStow());
//		climb.whenPressed(new Climb());
//		stopCollect.whenPressed(new StopCollect());
//		elevatorTopPyramidCube.whenPressed(new SetPotentialCollectorHeight(CollectorHeight.TOP));
//		elevatorMiddlePyramidCube.whenPressed(new SetPotentialCollectorHeight(CollectorHeight.MIDDLE));
//		elevatorBottomPyramidCube.whenPressed(new SetPotentialCollectorHeight(CollectorHeight.FLOOR));
//		openFingers.whenPressed(new OpenClaw());
//		openFingers.whenReleased(new CloseClaw());
//		spitSpeed.whenPressed(new SetEjectStrength(true));
//		spitSpeed.whenReleased(new SetEjectStrength(false));
//		elevatorAdjustUp.whileHeld(new AdjustElevatorHeight(5));
//		elevatorAdjustDown.whileHeld(new AdjustElevatorHeight(-5));
//		elevatorMaxHeight.whenPressed(new SetScaleOwnership(ScaleOwnership.DISOWNED_WORST));
//		disownedScale.whenPressed(new SetScaleOwnership(ScaleOwnership.DISOWNED));
//		neutralScale.whenPressed(new SetScaleOwnership(ScaleOwnership.NEUTRAL));
//		ownedScale.whenPressed(new SetScaleOwnership(ScaleOwnership.OWNED));		
	}
	
	public XboxController getDriver() {
		return driver;
	}
	
	public XboxController getCoDriver() {
		return coDriver;
	}
	
}

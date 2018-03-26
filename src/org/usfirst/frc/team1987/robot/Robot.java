/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1987.robot;

import org.usfirst.frc.team1987.robot.commands.auto.MiddleToRightSwitch;
import org.usfirst.frc.team1987.robot.commands.auto.MiddleToRightSwitchSwoop;
import org.usfirst.frc.team1987.robot.commands.auto.RightToLeftScale;
import org.usfirst.frc.team1987.robot.commands.auto.RightToRightScale2x;
import org.usfirst.frc.team1987.robot.commands.auto.RightToRightScale2xWithBackup;
import org.usfirst.frc.team1987.robot.commands.auto.RightToRightScaleAndBackup;
//import org.usfirst.frc.team1987.robot.commands.auto.LeftToRightScaleRightSwitch;
//import org.usfirst.frc.team1987.robot.commands.auto.MiddleToLeftSwitch;
//import org.usfirst.frc.team1987.robot.commands.auto.MiddleToRightSwitch;
//import org.usfirst.frc.team1987.robot.commands.auto.RightToLeftScale;
//import org.usfirst.frc.team1987.robot.commands.auto.LeftToRightScale;
//import org.usfirst.frc.team1987.robot.commands.auto.LeftToLeftScale;
//import org.usfirst.frc.team1987.robot.commands.auto.LeftToLeftScaleLeftSwitch;
//import org.usfirst.frc.team1987.robot.commands.auto.LeftToLeftScaleRightSwitch;
//import org.usfirst.frc.team1987.robot.commands.auto.RightToLeftScaleLeftSwitch;
//import org.usfirst.frc.team1987.robot.commands.auto.RightToRightSwitch;
//import org.usfirst.frc.team1987.robot.commands.drive.DriveDistance;
//import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.subsystems.Claw;
import org.usfirst.frc.team1987.robot.subsystems.Drive;
import org.usfirst.frc.team1987.robot.subsystems.Elevator;
//import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */

public class Robot extends TimedRobot {
	public static final PowerDistributionPanel pdp = new PowerDistributionPanel();
	public static final Compressor compressor = new Compressor();
	public static final Drive drive = new Drive();
	public static final Claw claw = new Claw();
	public static final Elevator elevator = new Elevator();
	public static OI oi;
	public static final AutonomousChooser autonomousChooser = new AutonomousChooser();
	
	private static ScaleOwnership scaleOwnership;
	private static CollectorHeight collectorHeight;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		setPeriod(RobotMap.period);
		oi = new OI();
		setCollectorHeight(CollectorHeight.FLOOR);
		setScaleOwnership(ScaleOwnership.NEUTRAL);
		autonomousChooser.onRobotInit();
		// chooser.addObject("My Auto", new MyAutoCommand());
//		m_chooser.addObject("toScale", new DrivePath(AutoPaths.toScale));
//		m_chooser.addObject("toScaleSwoop", new DrivePath(AutoPaths.toScaleSwoop));
//		m_chooser.addObject("to far scale", new DrivePath(AutoPaths.toFarScale));
//		m_chooser.addObject("dope auto and place", new GoToRightScaleAndPlace());
//		m_chooser.addObject("s path", new DrivePath(AutoPaths.sPath));
//		m_chooser.addObject("Go to Left Scale and Place", new GoToLeftScaleAndPlace());
//		m_chooser.addObject("Hella", new HellaLeftNearSwitchNearScale());
//		m_chooser.addObject("dope far yo", new DopeLeftFarScale());
//		m_chooser.addObject("rightStartToLeftScale", new DrivePath(AutoPaths.rightStartToLeftScale));
//		m_chooser.addDefault("rightStartToLeftScale", new RightStartToLeftScale());
//		m_chooser.addDefault("middleToLeftSwitchAndPlace", new MiddleToLeftScale());
//		m_chooser.addDefault("rightToRightSwitch", new RightToRightSwitch());
//		m_chooser.addDefault("right to left scale and place", new RightToLeftScale());
//		m_chooser.addDefault("right to left scale and left switch", new RightToLeftScaleLeftSwitch());
//		m_chooser.addDefault("left to right scale and switch", new LeftToRightScaleRightSwitch());
//		m_chooser.addDefault("left to left scale and place", new LeftToLeftScale());
//		m_chooser.addDefault("left 2 left scale and place", new GoToLeftScaleAndPlace());
//		m_chooser.addDefault("left to left scale left switch", new LeftToLeftScaleLeftSwitch());
//		m_chooser.addDefault("Middle to Left Switch", new MiddleToLeftSwitch());
//		m_chooser.addDefault("Middle to Right Switch", new MiddleToRightSwitch());
//		m_chooser.addDefault("left scale to right switch", new DrivePath(AutoPaths.leftScaleToRightSwitch));
//		m_chooser.addDefault("left to left scale to right switch", new LeftToLeftScaleRightSwitch());
		
//		SmartDashboard.putData("Auto mode", m_chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		compressor.setClosedLoopControl(true);
		drive.setHighGear();
		
		SmartDashboard.putString("borked", "disabled");
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
//		m_autonomousCommand = autonomousChooser.get();
		SmartDashboard.putString("borked", "auto");

		m_autonomousCommand = new RightToLeftScale();
//		m_autonomousCommand = new MiddleToRightSwitchSwoop();



		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		} else {
			System.out.println("Game Data not found at autonomousInit()");
		}
		compressor.setClosedLoopControl(false);
		compressor.stop();
		
		
//		ToggleDropDownOmniBack();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		if(m_autonomousCommand == null) {
			m_autonomousCommand = autonomousChooser.get();
			if(m_autonomousCommand != null) {
				m_autonomousCommand.start();
				System.out.println("Game Data found in autonomousPeriodic()");
			}
		}
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
//		drive.setLowGear();
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		
		SmartDashboard.putString("borked", "teleop");
		
		drive.setCoast();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	public static void setScaleOwnership(final ScaleOwnership newScaleOwnership) {
		scaleOwnership = newScaleOwnership;
	}
	
	public static ScaleOwnership getScaleOwnership() {
		return scaleOwnership;
	}
	
	public static void setCollectorHeight(final CollectorHeight newCollectorHeight) {
		collectorHeight = newCollectorHeight;
	}
	
	public static CollectorHeight getCollectorHeight() {
		return collectorHeight;
	}
}

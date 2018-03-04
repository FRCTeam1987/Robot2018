/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1987.robot;

import org.usfirst.frc.team1987.robot.commands.auto.DopeLeftFarScale;
import org.usfirst.frc.team1987.robot.commands.auto.GoToLeftScaleAndPlace;
import org.usfirst.frc.team1987.robot.commands.auto.GoToRightScaleAndPlace;
import org.usfirst.frc.team1987.robot.commands.auto.HellaLeftNearSwitchNearScale;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.subsystems.Claw;
import org.usfirst.frc.team1987.robot.subsystems.Drive;
import org.usfirst.frc.team1987.robot.subsystems.Elevator;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		// chooser.addObject("My Auto", new MyAutoCommand());
//		m_chooser.addObject("toScale", new DrivePath(AutoPaths.toScale));
//		m_chooser.addObject("toScaleSwoop", new DrivePath(AutoPaths.toScaleSwoop));
//		m_chooser.addObject("to far scale", new DrivePath(AutoPaths.toFarScale));
//		m_chooser.addObject("dope auto and place", new GoToRightScaleAndPlace());
//		m_chooser.addObject("s path", new DrivePath(AutoPaths.sPath));
//		m_chooser.addObject("Go to Left Scale and Place", new GoToLeftScaleAndPlace());
//		m_chooser.addObject("Hella", new HellaLeftNearSwitchNearScale());
		m_chooser.addObject("dope far yo", new DopeLeftFarScale());
		
		SmartDashboard.putData("Auto mode", m_chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		compressor.setClosedLoopControl(true);
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
//		drive.setLowGear();
		m_autonomousCommand = m_chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
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

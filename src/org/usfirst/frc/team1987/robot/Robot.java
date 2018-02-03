/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1987.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

import org.usfirst.frc.team1987.robot.commands.ExampleCommand;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.subsystems.Claw;
import org.usfirst.frc.team1987.robot.subsystems.Drive;
import org.usfirst.frc.team1987.robot.subsystems.Elevator;
import org.usfirst.frc.team1987.robot.subsystems.ExampleSubsystem;


public class Robot extends TimedRobot {
	public static final ExampleSubsystem kExampleSubsystem = new ExampleSubsystem();
	public static final PowerDistributionPanel pdp = new PowerDistributionPanel();
	public static final Drive drive = new Drive();
	public static final Elevator elevator = new Elevator();
	public static final Claw claw = new Claw();
	public static OI m_oi;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		setPeriod(RobotMap.period);
		m_oi = new OI();
		m_chooser.addDefault("Default Auto", new ExampleCommand());
		int a = 0;
		System.out.println(a);
//		m_chooser.addObject("trajectory", new DrivePath(new Waypoint[] {
//				new Waypoint(-3, -2, 0.0),	//x, y, degrees
//				new Waypoint(0, -1, 0.0),
//				new Waypoint(0, 0, Pathfinder.d2r(-45))
//		}));
//		try {
//		m_chooser.addObject("trajectory", new DrivePath(new Waypoint[] {
//				new Waypoint(2, -2, 0.0),	//x, y, degrees
////				new Waypoint(1, 1, Pathfinder.d2r(45)),
//				new Waypoint(0, 0, Pathfinder.d2r(90))
//		}));
//		} catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
		
		m_chooser.addObject("center-left-switch",  new DrivePath("/home/lvuser/paths/left-left.csv", "/home/lvuser/paths/left-right.csv"));
		m_chooser.addObject("center-right-switch",  new DrivePath("/home/lvuser/paths/right-left.csv", "/home/lvuser/paths/right-right.csv"));

		
		SmartDashboard.putData("Auto mode", m_chooser);
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();

		
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
		
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
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
}

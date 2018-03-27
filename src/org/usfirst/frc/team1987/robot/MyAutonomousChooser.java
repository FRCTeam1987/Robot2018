package org.usfirst.frc.team1987.robot;

import org.usfirst.frc.team1987.robot.commands.auto.MiddleToLeftSwitch;
import org.usfirst.frc.team1987.robot.commands.auto.MiddleToRightSwitch;
import org.usfirst.frc.team1987.robot.commands.drive.DriveDistance;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class MyAutonomousChooser {
	private final SendableChooser<Command> leftSwitchLeftScaleChooser;
	private final SendableChooser<Command> leftSwitchRightScaleChooser;
	private final SendableChooser<Command> rightSwitchLeftScaleChooser;
	private final SendableChooser<Command> rightSwitchRightScaleChooser;
	
	public MyAutonomousChooser() {
		leftSwitchLeftScaleChooser = new SendableChooser<Command>();
		leftSwitchRightScaleChooser = new SendableChooser<Command>();
		rightSwitchLeftScaleChooser = new SendableChooser<Command>();
		rightSwitchRightScaleChooser = new SendableChooser<Command>();
		
//		leftSwitchLeftScaleChooser.addDefault("Drive Straight", new DriveDistance(80));
//		leftSwitchLeftScaleChooser.addObject("Middle to Left Switch", new MiddleToLeftSwitch());
////		leftSwitchLeftScaleChooser.addDefault("Right to Left Switch", object);
//		leftSwitchLeftScaleChooser.addObject("Right to Left Scale", object);
//		leftSwitchLeftScaleChooser.addObject("Right to Left Scale(WIDE)", object);
//		leftSwitchLeftScaleChooser.addObject("Right to Left Scale Left Switch", object);
//		
//		leftSwitchRightScaleChooser.addDefault("Drive Straight", new DriveDistance(80));
//		leftSwitchRightScaleChooser.addObject("Middle to Left Switch", new MiddleToLeftSwitch());
////		leftSwitchRightScaleChooser.addObject("Right to Left Switch", object);
//		leftSwitchRightScaleChooser.addObject("Right to Right Scale", object);
//		leftSwitchRightScaleChooser.addObject("Right to Right Scale 2X", object);
//		leftSwitchRightScaleChooser.addObject("Right to Right Scale 2X(WIDE)", object);
//		
//		rightSwitchLeftScaleChooser.addDefault("Drive Straight", new DriveDistance(80));
//		rightSwitchLeftScaleChooser.addObject("Middle to Right Switch", new MiddleToRightSwitch());
//		rightSwitchLeftScaleChooser.addObject("Right to Right Switch", object);
//		rightSwitchLeftScaleChooser.addObject("Right to Left Scale", object);
//
//		rightSwitchRightScaleChooser.addDefault("Drive Straight", new DriveDistance(80));
//		rightSwitchRightScaleChooser.addObject("Middle to Right Switch", new MiddleToLeftSwitch());
//		rightSwitchRightScaleChooser.addObject("Right to Right Switch", object);
//		rightSwitchRightScaleChooser.addObject("Right to Right Scale", object);
//		rightSwitchRightScaleChooser.addObject("Right to Right Scale 2X", object);
//		rightSwitchRightScaleChooser.addObject("Right to Right Scale 2X(WIDE)", object);
//		rightSwitchRightScaleChooser.addObject("Right to Right Scale Right Switch", object);
		
	}
}

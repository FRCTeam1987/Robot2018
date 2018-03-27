package org.usfirst.frc.team1987.robot;

import org.usfirst.frc.team1987.robot.commands.auto.MiddleToLeftSwitch;
import org.usfirst.frc.team1987.robot.commands.auto.MiddleToRightSwitch;
import org.usfirst.frc.team1987.robot.commands.auto.RightToLeftScale;
import org.usfirst.frc.team1987.robot.commands.auto.RightToLeftScaleExtraSketch;
import org.usfirst.frc.team1987.robot.commands.auto.RightToLeftScaleLeftSwitch;
import org.usfirst.frc.team1987.robot.commands.auto.RightToLeftSwitch;
import org.usfirst.frc.team1987.robot.commands.auto.RightToRightScale;
import org.usfirst.frc.team1987.robot.commands.auto.RightToRightScale2x;
import org.usfirst.frc.team1987.robot.commands.auto.RightToRightScale2xWithBackup;
import org.usfirst.frc.team1987.robot.commands.auto.RightToRightScaleRightSwitch;
import org.usfirst.frc.team1987.robot.commands.auto.RightToRightSwitch;
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
		
		leftSwitchLeftScaleChooser.addDefault("Drive Straight", new DriveDistance(80));
		leftSwitchLeftScaleChooser.addObject("Middle to Left Switch", new MiddleToLeftSwitch());
		leftSwitchLeftScaleChooser.addObject("Right to Left Switch", new RightToLeftSwitch());
		leftSwitchLeftScaleChooser.addObject("Right to Left Scale", new RightToLeftScale());
		leftSwitchLeftScaleChooser.addObject("Right to Left Scale(WIDE)", new RightToLeftScaleExtraSketch());
		leftSwitchLeftScaleChooser.addObject("Right to Left Scale Left Switch", new RightToLeftScaleLeftSwitch());
		
		leftSwitchRightScaleChooser.addDefault("Drive Straight", new DriveDistance(80));
		leftSwitchRightScaleChooser.addObject("Middle to Left Switch", new MiddleToLeftSwitch());
		leftSwitchRightScaleChooser.addObject("Right to Left Switch", new RightToLeftSwitch());
		leftSwitchRightScaleChooser.addObject("Right to Right Scale", new RightToRightScale());
		leftSwitchRightScaleChooser.addObject("Right to Right Scale 2X", new RightToRightScale2x());
		leftSwitchRightScaleChooser.addObject("Right to Right Scale 2X(WIDE)", new RightToRightScale2xWithBackup());
		
		rightSwitchLeftScaleChooser.addDefault("Drive Straight", new DriveDistance(80));
		rightSwitchLeftScaleChooser.addObject("Middle to Right Switch", new MiddleToRightSwitch());
		rightSwitchLeftScaleChooser.addObject("Right to Right Switch", new RightToRightSwitch());	//needs testing
		rightSwitchLeftScaleChooser.addObject("Right to Left Scale", new RightToLeftScale());

		rightSwitchRightScaleChooser.addDefault("Drive Straight", new DriveDistance(80));
		rightSwitchRightScaleChooser.addObject("Middle to Right Switch", new MiddleToRightSwitch());
		rightSwitchRightScaleChooser.addObject("Right to Right Switch", new RightToRightSwitch());
		rightSwitchRightScaleChooser.addObject("Right to Right Scale", new RightToRightScale());
		rightSwitchRightScaleChooser.addObject("Right to Right Scale 2X", new RightToRightScale2x());
		rightSwitchRightScaleChooser.addObject("Right to Right Scale 2X(WIDE)", new RightToRightScale2xWithBackup());
		rightSwitchRightScaleChooser.addObject("Right to Right Scale Right Switch", new RightToRightScaleRightSwitch());
		
	}
}

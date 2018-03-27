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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import openrio.powerup.MatchData;

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
		
		leftSwitchLeftScaleChooser.addDefault("1. Drive Straight", new DriveDistance(80));				//ll = 6
		leftSwitchLeftScaleChooser.addObject("2. Middle to Left Switch", new MiddleToLeftSwitch());
		leftSwitchLeftScaleChooser.addObject("3. Right to Left Switch", new RightToLeftSwitch());
		leftSwitchLeftScaleChooser.addObject("4. Right to Left Scale", new RightToLeftScale());
		leftSwitchLeftScaleChooser.addObject("5. Right to Left Scale(WIDE)", new RightToLeftScaleExtraSketch());
		leftSwitchLeftScaleChooser.addObject("6. Right to Left Scale Left Switch", new RightToLeftScaleLeftSwitch());
		
		leftSwitchRightScaleChooser.addDefault("1. Drive Straight", new DriveDistance(80));			//lr = 6
		leftSwitchRightScaleChooser.addObject("2. Middle to Left Switch", new MiddleToLeftSwitch());
		leftSwitchRightScaleChooser.addObject("3. Right to Left Switch", new RightToLeftSwitch());
		leftSwitchRightScaleChooser.addObject("4. Right to Right Scale", new RightToRightScale());
		leftSwitchRightScaleChooser.addObject("5. Right to Right Scale 2X", new RightToRightScale2x());
		leftSwitchRightScaleChooser.addObject("6. Right to Right Scale 2X(WIDE)", new RightToRightScale2xWithBackup());
		
		rightSwitchLeftScaleChooser.addDefault("1. Drive Straight", new DriveDistance(80));			//rl = 4
		rightSwitchLeftScaleChooser.addObject("2. Middle to Right Switch", new MiddleToRightSwitch());
		rightSwitchLeftScaleChooser.addObject("3. Right to Right Switch", new RightToRightSwitch());	//needs testing
		rightSwitchLeftScaleChooser.addObject("4. Right to Left Scale", new RightToLeftScale());
		rightSwitchLeftScaleChooser.addObject("5. Right to Left Scale(WIDE)", new RightToLeftScaleExtraSketch());
		
		rightSwitchRightScaleChooser.addDefault("1. Drive Straight", new DriveDistance(80));			//rr = 7
		rightSwitchRightScaleChooser.addObject("2. Middle to Right Switch", new MiddleToRightSwitch());
		rightSwitchRightScaleChooser.addObject("3. Right to Right Switch", new RightToRightSwitch());
		rightSwitchRightScaleChooser.addObject("4. Right to Right Scale", new RightToRightScale());
		rightSwitchRightScaleChooser.addObject("5. Right to Right Scale 2X", new RightToRightScale2x());
		rightSwitchRightScaleChooser.addObject("6. Right to Right Scale 2X(WIDE)", new RightToRightScale2xWithBackup());
		rightSwitchRightScaleChooser.addObject("7. Right to Right Scale Right Switch", new RightToRightScaleRightSwitch());
	}
	
	public Command get() {
		MatchData.OwnedSide ourSwitch = MatchData.getOwnedSide(MatchData.GameFeature.SWITCH_NEAR);
		MatchData.OwnedSide scale = MatchData.getOwnedSide(MatchData.GameFeature.SCALE);
		
		if(ourSwitch == MatchData.OwnedSide.UNKNOWN || scale == MatchData.OwnedSide.UNKNOWN) {
			return null;
		}
		
		if(ourSwitch == MatchData.OwnedSide.LEFT && scale == MatchData.OwnedSide.LEFT) {
			SmartDashboard.putString("Selected Auto", leftSwitchLeftScaleChooser.getSelected().getName());
			return leftSwitchLeftScaleChooser.getSelected();
		}
		
		if(ourSwitch == MatchData.OwnedSide.LEFT && scale == MatchData.OwnedSide.RIGHT) {
			SmartDashboard.putString("Selected Auto", leftSwitchRightScaleChooser.getSelected().getName());
			return leftSwitchRightScaleChooser.getSelected();
		}
		
		if(ourSwitch == MatchData.OwnedSide.RIGHT && scale == MatchData.OwnedSide.LEFT) {
			SmartDashboard.putString("Selected Auto", rightSwitchLeftScaleChooser.getSelected().getName());
			return rightSwitchLeftScaleChooser.getSelected();
		}
		
		if(ourSwitch == MatchData.OwnedSide.RIGHT && scale == MatchData.OwnedSide.RIGHT) {
			SmartDashboard.putString("Selected Auto", rightSwitchRightScaleChooser.getSelected().getName());
			return rightSwitchRightScaleChooser.getSelected();
		}
		
		SmartDashboard.putString("Selected Auto", "default drive straight");
		
		return new DriveDistance(80);
	}
	
	public void onRobotInit() {
		SmartDashboard.putData("Left Switch Left Scale", leftSwitchLeftScaleChooser);
		SmartDashboard.putData("Left Switch Right Scale", leftSwitchRightScaleChooser);
		SmartDashboard.putData("Right Switch Left Scale", rightSwitchLeftScaleChooser);
		SmartDashboard.putData("Right Switch Right Scale", rightSwitchRightScaleChooser);
	}
}

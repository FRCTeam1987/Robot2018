package org.usfirst.frc.team1987.robot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.usfirst.frc.team1987.robot.commands.auto.LeftToLeftScale;
import org.usfirst.frc.team1987.robot.commands.auto.LeftToLeftScaleLeftSwitch;
import org.usfirst.frc.team1987.robot.commands.auto.LeftToLeftScaleRightSwitch;
import org.usfirst.frc.team1987.robot.commands.auto.LeftToLeftSwitch;
import org.usfirst.frc.team1987.robot.commands.auto.LeftToRightScale;
import org.usfirst.frc.team1987.robot.commands.auto.LeftToRightScaleRightSwitch;
import org.usfirst.frc.team1987.robot.commands.auto.MiddleToLeftSwitch;
import org.usfirst.frc.team1987.robot.commands.auto.MiddleToRightSwitch;
import org.usfirst.frc.team1987.robot.commands.auto.RightToLeftScale;
import org.usfirst.frc.team1987.robot.commands.auto.RightToLeftScaleLeftSwitch;
import org.usfirst.frc.team1987.robot.commands.auto.RightToRightScale;
import org.usfirst.frc.team1987.robot.commands.auto.RightToRightScale2x;
import org.usfirst.frc.team1987.robot.commands.auto.RightToRightScaleRightSwitch;
import org.usfirst.frc.team1987.robot.commands.auto.RightToRightSwitch;
import org.usfirst.frc.team1987.robot.commands.drive.DriveDistance;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import openrio.powerup.MatchData;
import openrio.powerup.MatchData.OwnedSide;

public class AutonomousChooser {

	private final SendableChooser<StartingPosition> startingPositionChooser;
	private final SendableChooser<AutonomousMode> modeChooser;
	private final List<AutonomousContainer> autonomousCommandList;
	
	public AutonomousChooser() {
		startingPositionChooser = new SendableChooser<StartingPosition>();
		modeChooser = new SendableChooser<AutonomousMode>();
		autonomousCommandList = new ArrayList<AutonomousContainer>();
		
		startingPositionChooser.addDefault("Right", StartingPosition.Right);
		startingPositionChooser.addObject("Left", StartingPosition.Left);
		startingPositionChooser.addObject("Middle", StartingPosition.Middle);
		
		modeChooser.addDefault("Both", AutonomousMode.Both);
		modeChooser.addObject("Near", AutonomousMode.Near);
		modeChooser.addObject("Scale", AutonomousMode.Scale);
		modeChooser.addObject("Switch", AutonomousMode.Switch);
		
///////////////////////////// Right Switch ///////////////////////////////////////////
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Right Straight (1,4)")	// good
			.withStartingPosition(StartingPosition.Right)
			.withMode(AutonomousMode.Switch)
			.withSwitchPosition(OwnedSide.LEFT)
			.withScalePosition(OwnedSide.LEFT)
			.withCommand(new DriveDistance(80))
			.build());

		autonomousCommandList.add(
		new AutonomousContainer.Builder("Right Straight (2,4)")	// good
			.withStartingPosition(StartingPosition.Right)
			.withMode(AutonomousMode.Switch)
			.withSwitchPosition(OwnedSide.LEFT)
			.withScalePosition(OwnedSide.RIGHT)
			.withCommand(new DriveDistance(80))
			.build());
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Right to Right Switch (3,4)")	// good
			.withStartingPosition(StartingPosition.Right)
			.withMode(AutonomousMode.Switch)
			.withSwitchPosition(OwnedSide.RIGHT)
			.withScalePosition(OwnedSide.LEFT)
			.withCommand(new RightToRightSwitch())
			.build());
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Right to Right Switch (4,4)")	// good
			.withStartingPosition(StartingPosition.Right)
			.withMode(AutonomousMode.Switch)
			.withSwitchPosition(OwnedSide.RIGHT)
			.withScalePosition(OwnedSide.RIGHT)
			.withCommand(new RightToRightSwitch())
			.build());
		
/////////////////////////// Right Scale ///////////////////////////////////////////////////////
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Right to Left Scale (1,3)")	// good
			.withStartingPosition(StartingPosition.Right)
			.withMode(AutonomousMode.Scale)
			.withSwitchPosition(OwnedSide.LEFT)
			.withScalePosition(OwnedSide.LEFT)
			.withCommand(new RightToLeftScale())
			.build());

		autonomousCommandList.add(
		new AutonomousContainer.Builder("Right to Right Scale (2,3)")	// good
			.withStartingPosition(StartingPosition.Right)
			.withMode(AutonomousMode.Scale)
			.withSwitchPosition(OwnedSide.LEFT)
			.withScalePosition(OwnedSide.RIGHT)
			.withCommand(new RightToRightScale())
			.build());
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Right to Left Scale (3,3)")	// good
			.withStartingPosition(StartingPosition.Right)
			.withMode(AutonomousMode.Scale)
			.withSwitchPosition(OwnedSide.RIGHT)
			.withScalePosition(OwnedSide.LEFT)
			.withCommand(new RightToLeftScale())
			.build());
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Right to Right Scale 2X (4,3)")	// good
			.withStartingPosition(StartingPosition.Right)
			.withMode(AutonomousMode.Scale)
			.withSwitchPosition(OwnedSide.RIGHT)
			.withScalePosition(OwnedSide.RIGHT)
			.withCommand(new RightToRightScale2x())
			.build());		
		
///////////////////////////////////// Right Both /////////////////////////////////////////////////		
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Right To Left Scale To Left Switch (1,2)")	//good
			.withStartingPosition(StartingPosition.Right)
			.withMode(AutonomousMode.Both)
			.withSwitchPosition(OwnedSide.LEFT)
			.withScalePosition(OwnedSide.LEFT)
			.withCommand(new RightToLeftScaleLeftSwitch())
			.build());
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Right to Right Scale (2,2)")	//good
			.withStartingPosition(StartingPosition.Right)		
			.withMode(AutonomousMode.Both)
			.withSwitchPosition(OwnedSide.LEFT)
			.withScalePosition(OwnedSide.RIGHT)
			.withCommand(new RightToRightScale())
			.build());
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Right To Right Switch (3,2)")	//good
			.withStartingPosition(StartingPosition.Right)
			.withMode(AutonomousMode.Both)
			.withSwitchPosition(OwnedSide.RIGHT)
			.withScalePosition(OwnedSide.LEFT)
			.withCommand(new RightToRightSwitch())
			.build());
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Right To Right Scale Right Switch (4,2)")	//good
			.withStartingPosition(StartingPosition.Right)
			.withMode(AutonomousMode.Both)
			.withSwitchPosition(OwnedSide.RIGHT)
			.withScalePosition(OwnedSide.RIGHT)
			.withCommand(new RightToRightScaleRightSwitch())
			.build());		
		
///////////////////////////////////// Right Near ///////////////////////////////////////////////		
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Right Straight (1,1)")	// good
			.withStartingPosition(StartingPosition.Right)
			.withMode(AutonomousMode.Near)
			.withSwitchPosition(OwnedSide.LEFT)
			.withScalePosition(OwnedSide.LEFT)
			.withCommand(new DriveDistance(80))
			.build());

		autonomousCommandList.add(
		new AutonomousContainer.Builder("Right to Right Scale (2,1)")	// good
			.withStartingPosition(StartingPosition.Right)
			.withMode(AutonomousMode.Near)
			.withSwitchPosition(OwnedSide.LEFT)
			.withScalePosition(OwnedSide.RIGHT)
			.withCommand(new RightToRightScale())
			.build());
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Right to Right Switch (3,1)")	// good
			.withStartingPosition(StartingPosition.Right)
			.withMode(AutonomousMode.Near)
			.withSwitchPosition(OwnedSide.RIGHT)
			.withScalePosition(OwnedSide.LEFT)
			.withCommand(new RightToRightSwitch())
			.build());
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Right to Right Scale Right Switch (4,1)")	// good
			.withStartingPosition(StartingPosition.Right)
			.withMode(AutonomousMode.Near)
			.withSwitchPosition(OwnedSide.RIGHT)
			.withScalePosition(OwnedSide.RIGHT)
			.withCommand(new RightToRightScaleRightSwitch())
			.build());		
		
/////////////////////////////////////// Middle /////////////////////////////////////////////////////		
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Middle To Left Switch")	//good
			.withStartingPosition(StartingPosition.Middle)
			.withMode(AutonomousMode.Switch)
			.withSwitchPosition(OwnedSide.LEFT)
			.withScalePosition(OwnedSide.UNKNOWN)
			.withCommand(new MiddleToLeftSwitch())
			.build());

		autonomousCommandList.add(
		new AutonomousContainer.Builder("Middle To Right Switch")	//good
			.withStartingPosition(StartingPosition.Middle)
			.withMode(AutonomousMode.Switch)
			.withSwitchPosition(OwnedSide.RIGHT)
			.withScalePosition(OwnedSide.UNKNOWN)
			.withCommand(new MiddleToRightSwitch())
			.build());

///////////////////////////////////// Left Switch ////////////////////////////////////////////////		
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Left to Left Scale (1,4)")	// good
			.withStartingPosition(StartingPosition.Left)
			.withMode(AutonomousMode.Switch)
			.withSwitchPosition(OwnedSide.LEFT)
			.withScalePosition(OwnedSide.LEFT)
			.withCommand(new LeftToLeftScale())
			.build());

		autonomousCommandList.add(
		new AutonomousContainer.Builder("Left Left Scale (2,4)")	// good
			.withStartingPosition(StartingPosition.Left)
			.withMode(AutonomousMode.Switch)
			.withSwitchPosition(OwnedSide.LEFT)
			.withScalePosition(OwnedSide.RIGHT)
			.withCommand(new LeftToLeftScale())
			.build());
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Left Straight (3,4)")	// good
			.withStartingPosition(StartingPosition.Left)
			.withMode(AutonomousMode.Switch)
			.withSwitchPosition(OwnedSide.RIGHT)
			.withScalePosition(OwnedSide.LEFT)
			.withCommand(new DriveDistance(80))
			.build());
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Left Straight (4,4)")	// good
			.withStartingPosition(StartingPosition.Left)
			.withMode(AutonomousMode.Switch)
			.withSwitchPosition(OwnedSide.RIGHT)
			.withScalePosition(OwnedSide.RIGHT)
			.withCommand(new DriveDistance(80))
			.build());
		
/////////////////////////// Left Scale ///////////////////////////////////////////////////////
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Left to Left Scale (1,3)")	// good
			.withStartingPosition(StartingPosition.Left)
			.withMode(AutonomousMode.Scale)
			.withSwitchPosition(OwnedSide.LEFT)
			.withScalePosition(OwnedSide.LEFT)
			.withCommand(new LeftToLeftScale())
			.build());

		autonomousCommandList.add(
		new AutonomousContainer.Builder("Left to Right Scale (2,3)")	// good
			.withStartingPosition(StartingPosition.Left)
			.withMode(AutonomousMode.Scale)
			.withSwitchPosition(OwnedSide.LEFT)
			.withScalePosition(OwnedSide.RIGHT)
			.withCommand(new LeftToRightScale())
			.build());
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Left to Left Scale (3,3)")	// good
			.withStartingPosition(StartingPosition.Left)
			.withMode(AutonomousMode.Scale)
			.withSwitchPosition(OwnedSide.RIGHT)
			.withScalePosition(OwnedSide.LEFT)
			.withCommand(new LeftToLeftScale())
			.build());
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Left to Right Scale (4,3)")	// good
			.withStartingPosition(StartingPosition.Left)
			.withMode(AutonomousMode.Scale)
			.withSwitchPosition(OwnedSide.RIGHT)
			.withScalePosition(OwnedSide.RIGHT)
			.withCommand(new LeftToRightScale())
			.build());		
		
///////////////////////////////////// Left Both /////////////////////////////////////////////////		
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Left to Left Scale Left Switch (1,2)")	//good
			.withStartingPosition(StartingPosition.Left)
			.withMode(AutonomousMode.Both)
			.withSwitchPosition(OwnedSide.LEFT)
			.withScalePosition(OwnedSide.LEFT)
			.withCommand(new LeftToLeftScaleLeftSwitch())
			.build());
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Left to Left Switch (2,2)")	//good
			.withStartingPosition(StartingPosition.Left)		
			.withMode(AutonomousMode.Both)
			.withSwitchPosition(OwnedSide.LEFT)
			.withScalePosition(OwnedSide.RIGHT)
			.withCommand(new LeftToLeftSwitch())
			.build());
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Left to Left Scale (3,2)")	//good
			.withStartingPosition(StartingPosition.Left)
			.withMode(AutonomousMode.Both)
			.withSwitchPosition(OwnedSide.RIGHT)
			.withScalePosition(OwnedSide.LEFT)
			.withCommand(new LeftToLeftScale())
			.build());
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Left to Right Scale Right Switch (4,2)")	//good
			.withStartingPosition(StartingPosition.Left)
			.withMode(AutonomousMode.Both)
			.withSwitchPosition(OwnedSide.RIGHT)
			.withScalePosition(OwnedSide.RIGHT)
			.withCommand(new LeftToRightScaleRightSwitch())
			.build());		
		
///////////////////////////////////// Left Near ///////////////////////////////////////////////		
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Left to Left Scale Left Switch (1,1)")	// good
			.withStartingPosition(StartingPosition.Left)
			.withMode(AutonomousMode.Near)
			.withSwitchPosition(OwnedSide.LEFT)
			.withScalePosition(OwnedSide.LEFT)
			.withCommand(new LeftToLeftScaleLeftSwitch())
			.build());

		autonomousCommandList.add(
		new AutonomousContainer.Builder("Left to Left Switch (2,1)")	// good
			.withStartingPosition(StartingPosition.Left)
			.withMode(AutonomousMode.Near)
			.withSwitchPosition(OwnedSide.LEFT)
			.withScalePosition(OwnedSide.RIGHT)
			.withCommand(new LeftToLeftSwitch())
			.build());
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Left to Left Scale (3,1)")	// good
			.withStartingPosition(StartingPosition.Left)
			.withMode(AutonomousMode.Near)
			.withSwitchPosition(OwnedSide.RIGHT)
			.withScalePosition(OwnedSide.LEFT)
			.withCommand(new LeftToLeftScale())
			.build());
		
		autonomousCommandList.add(
		new AutonomousContainer.Builder("Left Straight (4,1)")	// good
			.withStartingPosition(StartingPosition.Right)
			.withMode(AutonomousMode.Near)
			.withSwitchPosition(OwnedSide.RIGHT)
			.withScalePosition(OwnedSide.RIGHT)
			.withCommand(new DriveDistance(80))
			.build());		

//		autonomousCommandList.add(
//				new AutonomousContainer.Builder("Right to Right Scale")	//good
//					.withStartingPosition(StartingPosition.Right)		
//					.withMode(AutonomousMode.Both)
//					.withSwitchPosition(OwnedSide.LEFT)
//					.withScalePosition(OwnedSide.RIGHT)
//					.withCommand(new RightToRightScale())
//					.build());
//		
//		autonomousCommandList.add(
//			new AutonomousContainer.Builder("Left To Left Scale")
//				.withStartingPosition(StartingPosition.Left)
//				.withMode(AutonomousMode.Scale)
//				.withSwitchPosition(OwnedSide.UNKNOWN)
//				.withScalePosition(OwnedSide.LEFT)
//				.withCommand(new LeftToLeftScale())
//				.build());
//		
//		autonomousCommandList.add(
//				new AutonomousContainer.Builder("Left To Left Switch")
//					.withStartingPosition(StartingPosition.Left)
//					.withMode(AutonomousMode.Switch)
//					.withSwitchPosition(OwnedSide.LEFT)
//					.withScalePosition(OwnedSide.UNKNOWN)
//					.withCommand(new LeftToLeftSwitch())
//					.build());
//		
//		autonomousCommandList.add(
//				new AutonomousContainer.Builder("Left To Left Switch - Ignore Right Scale")	
//					.withStartingPosition(StartingPosition.Left)
//					.withMode(AutonomousMode.Both)
//					.withSwitchPosition(OwnedSide.LEFT)
//					.withScalePosition(OwnedSide.RIGHT)
//					.withCommand(new LeftToLeftSwitch())
//					.build());
//		
//		autonomousCommandList.add(
//				new AutonomousContainer.Builder("Left To Left Scale Left Switch")	
//					.withStartingPosition(StartingPosition.Left)
//					.withMode(AutonomousMode.Both)
//					.withSwitchPosition(OwnedSide.LEFT)
//					.withScalePosition(OwnedSide.LEFT)
//					.withCommand(new LeftToLeftScaleLeftSwitch())
//					.build());
//		
//		autonomousCommandList.add(
//				new AutonomousContainer.Builder("Left To Left Scale Right Switch")
//					.withStartingPosition(StartingPosition.Left)
//					.withMode(AutonomousMode.Both)
//					.withSwitchPosition(OwnedSide.RIGHT)
//					.withScalePosition(OwnedSide.LEFT)
//					.withCommand(new LeftToLeftScaleRightSwitch())
//					.build());
//		
//		autonomousCommandList.add(
//				new AutonomousContainer.Builder("Left To Right Scale")
//					.withStartingPosition(StartingPosition.Left)
//					.withMode(AutonomousMode.Scale)
//					.withSwitchPosition(OwnedSide.UNKNOWN)
//					.withScalePosition(OwnedSide.RIGHT)
//					.withCommand(new LeftToRightScale())
//					.build());
//		
//		autonomousCommandList.add(
//				new AutonomousContainer.Builder("Left To Right Scale To Right Switch")
//					.withStartingPosition(StartingPosition.Left)
//					.withMode(AutonomousMode.Both)
//					.withSwitchPosition(OwnedSide.RIGHT)
//					.withScalePosition(OwnedSide.RIGHT)
//					.withCommand(new LeftToRightScaleRightSwitch())
//					.build());
//		
//		autonomousCommandList.add(
//				new AutonomousContainer.Builder("Middle To Left Switch")	//good
//					.withStartingPosition(StartingPosition.Middle)
//					.withMode(AutonomousMode.Switch)
//					.withSwitchPosition(OwnedSide.LEFT)
//					.withScalePosition(OwnedSide.UNKNOWN)
//					.withCommand(new MiddleToLeftSwitch())
//					.build());
//		
//		autonomousCommandList.add(
//				new AutonomousContainer.Builder("Middle To Right Switch")	//good
//					.withStartingPosition(StartingPosition.Middle)
//					.withMode(AutonomousMode.Switch)
//					.withSwitchPosition(OwnedSide.RIGHT)
//					.withScalePosition(OwnedSide.UNKNOWN)
//					.withCommand(new MiddleToRightSwitch())
//					.build());
//		
//		autonomousCommandList.add(
//				new AutonomousContainer.Builder("Right To Left Scale")
//					.withStartingPosition(StartingPosition.Right)
//					.withMode(AutonomousMode.Scale)
//					.withSwitchPosition(OwnedSide.UNKNOWN)
//					.withScalePosition(OwnedSide.LEFT)
//					.withCommand(new RightToLeftScale())
//					.build());
//		
//		autonomousCommandList.add(
//				new AutonomousContainer.Builder("Right To Left Scale To Left Switch")	//good
//					.withStartingPosition(StartingPosition.Right)
//					.withMode(AutonomousMode.Both)
//					.withSwitchPosition(OwnedSide.LEFT)
//					.withScalePosition(OwnedSide.LEFT)
//					.withCommand(new RightToLeftScaleLeftSwitch())
//					.build());
//		
//		autonomousCommandList.add(
//				new AutonomousContainer.Builder("Right To Right Scale")
//					.withStartingPosition(StartingPosition.Right)
//					.withMode(AutonomousMode.Scale)
//					.withSwitchPosition(OwnedSide.UNKNOWN)
//					.withScalePosition(OwnedSide.RIGHT)
//					.withCommand(new RightToRightScale2x())
//					.withCommand(new RightToRightScale())
//					.build());
//		
//		autonomousCommandList.add(
//				new AutonomousContainer.Builder("Right To Right Switch")
//					.withStartingPosition(StartingPosition.Right)
//					.withMode(AutonomousMode.Switch)
//					.withSwitchPosition(OwnedSide.RIGHT)
//					.withScalePosition(OwnedSide.UNKNOWN)
//					.withCommand(new RightToRightSwitch())
//					.build());
//		
//		autonomousCommandList.add(
//				new AutonomousContainer.Builder("Right To Right Switch - Ignore Left Scale")	//good
//					.withStartingPosition(StartingPosition.Right)
//					.withMode(AutonomousMode.Both)
//					.withSwitchPosition(OwnedSide.RIGHT)
//					.withScalePosition(OwnedSide.LEFT)
//					.withCommand(new RightToRightSwitch())
//					.build());
//		
//		autonomousCommandList.add(
//				new AutonomousContainer.Builder("Right To Right Scale Right Switch")	//good
//					.withStartingPosition(StartingPosition.Right)
//					.withMode(AutonomousMode.Both)
//					.withSwitchPosition(OwnedSide.RIGHT)
//					.withScalePosition(OwnedSide.RIGHT)
//					.withCommand(new RightToRightScaleRightSwitch())
//					.build());
	}
	
	public Command get() {
		MatchData.OwnedSide ourSwitch = MatchData.getOwnedSide(MatchData.GameFeature.SWITCH_NEAR);
		MatchData.OwnedSide scale = MatchData.getOwnedSide(MatchData.GameFeature.SCALE);
		
		if(ourSwitch == MatchData.OwnedSide.UNKNOWN || scale == MatchData.OwnedSide.UNKNOWN) {
			return null;
		}
		
		List<AutonomousContainer> possibleAutos;
		
		if(modeChooser.getSelected().equals(AutonomousMode.Near)) { // TODO: This logic is borked, lets duplicates of other side in...
			possibleAutos = autonomousCommandList.stream()
				.filter(auto -> startingPositionChooser.getSelected().equals(auto.getStartingPosition()) &&
					startingPositionChooser.getSelected().equals(StartingPosition.Middle) == false)
				.filter(auto -> (
					auto.getSwitchPosition() == MatchData.OwnedSide.LEFT &&
					auto.getStartingPosition() == StartingPosition.Left
				) || (
					auto.getSwitchPosition() == MatchData.OwnedSide.RIGHT &&
					auto.getStartingPosition() == StartingPosition.Right
				) || (
					(auto.getStartingPosition() == StartingPosition.Left && auto.getSwitchPosition() != MatchData.OwnedSide.RIGHT) ||
					(auto.getStartingPosition() == StartingPosition.Right && auto.getSwitchPosition() != MatchData.OwnedSide.LEFT)
				))
				.filter(auto -> (
					auto.getScalePosition() == MatchData.OwnedSide.LEFT &&
					auto.getStartingPosition() == StartingPosition.Left
				) || (
					auto.getScalePosition() == MatchData.OwnedSide.RIGHT &&
					auto.getStartingPosition() == StartingPosition.Right
				) || (
					(auto.getStartingPosition() == StartingPosition.Left && auto.getScalePosition() != MatchData.OwnedSide.RIGHT) ||
					(auto.getStartingPosition() == StartingPosition.Right && auto.getScalePosition() != MatchData.OwnedSide.LEFT)
				))
				.collect(Collectors.toList());
		} else {
			possibleAutos = autonomousCommandList.stream()
				.filter(auto -> startingPositionChooser.getSelected().equals(auto.getStartingPosition()))
				.filter(auto -> modeChooser.getSelected().equals(auto.getMode()))
				.filter(auto -> auto.getSwitchPosition() == ourSwitch)
				.filter(auto -> auto.getScalePosition() == scale || auto.getScalePosition() == MatchData.OwnedSide.UNKNOWN)
				.collect(Collectors.toList());
		}
		
		if(possibleAutos.size() == 0) {
			SmartDashboard.putString("Selected Auto", "Line Cross");
			return new DriveDistance(80);
		} else if(possibleAutos.size() > 1) {
			System.out.println("More than one!");
			possibleAutos.forEach(auto -> System.out.println(auto.getName()));
		} else {
			System.out.println("Exactly one!");
		}
		
		SmartDashboard.putString("Selected Auto", possibleAutos.get(0).getName());
		return possibleAutos.get(0).getCommand();
	}
	
	public void onRobotInit() {
		SmartDashboard.putData("Starting Position", startingPositionChooser);
		SmartDashboard.putData("Auto Mode", modeChooser);
	}
}

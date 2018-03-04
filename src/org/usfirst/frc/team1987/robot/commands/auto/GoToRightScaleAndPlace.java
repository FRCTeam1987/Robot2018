package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.ScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.SetScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.claw.EjectCube;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.elevator.GoToScaleHeight;
import org.usfirst.frc.team1987.util.AutoPaths;
import edu.wpi.first.wpilibj.command.WaitCommand;


import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToRightScaleAndPlace extends CommandGroup {

	public GoToRightScaleAndPlace() {
		addSequential(new SetScaleOwnership(ScaleOwnership.DISOWNED));
		addSequential(new DrivePath(AutoPaths.toFarScale));
//		addSequential(new WaitCommand(2));
//		addSequential(new GoToScaleHeight());
//		addSequential(new EjectCube());
	}
}

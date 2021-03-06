package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.DriveMode;
import org.usfirst.frc.team1987.robot.ScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.SetScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.claw.EjectCube;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team1987.robot.commands.elevator.GoToScaleHeight;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftToRightScale extends CommandGroup {

	public LeftToRightScale() {
    	addSequential(new ShiftHigh());
		addSequential(new SetScaleOwnership(ScaleOwnership.DISOWNED));
    	System.out.println("left to right scale");
		addSequential(new DrivePath(AutoPaths.leftToRightScale, DriveMode.DRIVEPATHTURNS));
		addSequential(new GoToScaleHeight());
		addSequential(new EjectCube());
	}
}

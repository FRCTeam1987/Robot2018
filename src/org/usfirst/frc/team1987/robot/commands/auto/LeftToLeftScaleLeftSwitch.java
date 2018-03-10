package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.ScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.DisableCompressor;
import org.usfirst.frc.team1987.robot.commands.EnableCompressor;
import org.usfirst.frc.team1987.robot.commands.SetScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.claw.EjectAndJiggle;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePivot;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team1987.robot.commands.elevator.GoToScaleHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftToLeftScaleLeftSwitch extends CommandGroup {

    public LeftToLeftScaleLeftSwitch() {
    	addSequential(new DisableCompressor());
    	addSequential(new SetScaleOwnership(ScaleOwnership.DISOWNED));
    	addSequential(new DrivePath(AutoPaths.leftToLeftScale));
    	addSequential(new GoToScaleHeight());
        addSequential(new EjectAndJiggle());
        addParallel(new SetElevatorHeight(0));
        addSequential(new ShiftLow());
        addSequential(new DrivePivot(130));
        addSequential(new ShiftHigh());
        addSequential(new EnableCompressor());
    }
}

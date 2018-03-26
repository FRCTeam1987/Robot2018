package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.DriveMode;
import org.usfirst.frc.team1987.robot.ScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.SetScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.claw.EjectAndJiggle;
import org.usfirst.frc.team1987.robot.commands.claw.WristStow;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePivot;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team1987.robot.commands.elevator.GoToScaleHeightInstant;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeightInstant;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightToRightScale2xWithBackup extends CommandGroup {

    public RightToRightScale2xWithBackup() {
        addSequential(new RightToRightScaleAndBackup());
        addSequential(new WristStow());
        addSequential(new ShiftLow());
        addSequential(new DrivePath(AutoPaths.rightSwitchToRightScale, DriveMode.DRIVEPATHLOW, true));
        addSequential(new SetScaleOwnership(ScaleOwnership.NEUTRAL));
        addSequential(new GoToScaleHeightInstant());
        addSequential(new DrivePivot(110));
        addSequential(new EjectAndJiggle());
        addSequential(new SetElevatorHeightInstant(0.0));
    }
}

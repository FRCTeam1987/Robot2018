package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.DriveMode;
import org.usfirst.frc.team1987.robot.ScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.SetScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.claw.EjectAndJiggle;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team1987.robot.commands.elevator.DelayGoToScaleHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.GoToScaleHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeightInstant;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightToRightScale extends CommandGroup { // UNTESTED

    public RightToRightScale() {
    	addSequential(new ShiftHigh());
        addSequential(new SetScaleOwnership(ScaleOwnership.DISOWNED));
    	System.out.println("right to right scale");
        addParallel(new DrivePath(AutoPaths.rightToRightScale, DriveMode.DRIVEPATHSTRAIGHT));
        addSequential(new DelayGoToScaleHeight(3.1));
     // Experimenting... change parallel back to sequential and use regular go to scale height if you want
//        addSequential(new GoToScaleHeight());
        addSequential(new EjectAndJiggle());
        addSequential(new SetElevatorHeightInstant(0));
    }
}

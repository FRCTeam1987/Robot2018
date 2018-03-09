package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.ScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.SetScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.claw.EjectAndJiggle;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.elevator.GoToScaleHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftToLeftScale extends CommandGroup {

    public LeftToLeftScale() {
    	addSequential(new SetScaleOwnership(ScaleOwnership.DISOWNED));
    	addSequential(new DrivePath(AutoPaths.toNearScale));
    	addSequential(new GoToScaleHeight());
        addSequential(new EjectAndJiggle());
        addSequential(new SetElevatorHeight(0));
    }
}

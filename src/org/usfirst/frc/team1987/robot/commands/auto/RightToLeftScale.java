package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.DriveMode;
import org.usfirst.frc.team1987.robot.ScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.SetScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.claw.EjectAndJiggle;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team1987.robot.commands.drive.ToggleDropDownOmniBack;
import org.usfirst.frc.team1987.robot.commands.elevator.GoToScaleHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeightInstant;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightToLeftScale extends CommandGroup {

    public RightToLeftScale() {
    	addSequential(new ShiftHigh());
    	addSequential(new SetScaleOwnership(ScaleOwnership.DISOWNED));
    	System.out.println("Right To Left Scale");
    	addSequential(new DrivePath(AutoPaths.rightToLeftScale, DriveMode.DRIVEPATHTURNS));
    	addSequential(new GoToScaleHeight());
    	addSequential(new EjectAndJiggle());
    	addSequential(new SetElevatorHeightInstant(0));
    }
}

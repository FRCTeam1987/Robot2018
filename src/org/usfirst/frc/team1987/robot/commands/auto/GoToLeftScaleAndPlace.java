package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.ScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.SetScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.claw.EjectCube;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.elevator.GoToScaleHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GoToLeftScaleAndPlace extends CommandGroup {

    public GoToLeftScaleAndPlace() {
    	addSequential(new SetScaleOwnership(ScaleOwnership.NEUTRAL));
        addSequential(new DrivePath(AutoPaths.toScaleSwoop));
        addSequential(new GoToScaleHeight());
        addSequential(new EjectCube());
    }
}

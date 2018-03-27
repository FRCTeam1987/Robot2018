package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.DriveMode;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.commands.claw.EjectAndJiggle;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeightInstant;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightToLeftSwitch extends CommandGroup {

    public RightToLeftSwitch() {
    	addSequential(new ShiftHigh());
    	addSequential(new SetElevatorHeightInstant(RobotMap.elevatorHoldCubeHeight));
    	System.out.println("right to left switch");
        addSequential(new DrivePath(AutoPaths.rightToLeftSwitch, DriveMode.DRIVEPATHTURNS));
    	addSequential(new EjectAndJiggle());
    }
}

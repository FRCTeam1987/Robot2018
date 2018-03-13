package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.commands.claw.EjectCube;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeightInstant;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftToLeftSwitch extends CommandGroup {

    public LeftToLeftSwitch() {
    	addSequential(new SetElevatorHeightInstant(RobotMap.elevatorHoldCubeHeight));
        addSequential(new DrivePath(AutoPaths.leftToLeftSwitch));
        addSequential(new EjectCube());
    }
}

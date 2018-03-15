package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.DriveMode;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.commands.claw.EjectCube;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeightInstant;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightToRightSwitch extends CommandGroup {

    public RightToRightSwitch() {
    	addSequential(new SetElevatorHeightInstant(RobotMap.elevatorHoldCubeHeight));
    	System.out.println("right ot right switch");
        addSequential(new DrivePath(AutoPaths.rightToRightSwitch, DriveMode.DRIVEPATHSTRAIGHT));
        addSequential(new EjectCube());
    }
}

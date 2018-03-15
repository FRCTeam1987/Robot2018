package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.DriveMode;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.commands.claw.EjectAndJiggle;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeightInstant;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleToRightSwitch extends CommandGroup {

    public MiddleToRightSwitch() {
    	addSequential(new ShiftLow());
    	addSequential(new SetElevatorHeightInstant(RobotMap.elevatorHoldCubeHeight));
    	System.out.println("right switch from middle");
    	addSequential(new DrivePath(AutoPaths.rightSwitchFromMiddle, DriveMode.DRIVEPATHLOW));
    	addSequential(new EjectAndJiggle());
    }
}

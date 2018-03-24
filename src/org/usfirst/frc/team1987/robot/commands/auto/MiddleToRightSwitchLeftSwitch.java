package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.DriveMode;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.commands.claw.AutoCollectCubeWide;
import org.usfirst.frc.team1987.robot.commands.claw.CollectStowedWithoutFingers;
import org.usfirst.frc.team1987.robot.commands.claw.EjectAndJiggle;
import org.usfirst.frc.team1987.robot.commands.claw.WristStow;
import org.usfirst.frc.team1987.robot.commands.drive.DriveDistance;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePivot;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeightInstant;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleToRightSwitchLeftSwitch extends CommandGroup {

    public MiddleToRightSwitchLeftSwitch() {
    	addSequential(new ShiftLow());
    	addSequential(new SetElevatorHeightInstant(RobotMap.elevatorHoldCubeHeight));
    	System.out.println("right switch from middle");
    	addSequential(new DrivePath(AutoPaths.rightSwitchFromMiddle, DriveMode.DRIVEPATHLOW));
    	addSequential(new EjectAndJiggle());
    	addSequential(new ShiftHigh());
    	System.out.println("righ switch to left switch");
    	addSequential(new DrivePath(AutoPaths.rightSwitchToLeftSwitchCube, DriveMode.DRIVEPATHTURNS, true));
    	addParallel(new SetElevatorHeightInstant(0));
    	addSequential(new DrivePivot(-47));
    	addParallel(new AutoCollectCubeWide());
    	addSequential(new DriveDistance(10));
    	addSequential(new WristStow());
    	addSequential(new DriveDistance(-6));
    	addSequential(new CollectStowedWithoutFingers());
    }
}

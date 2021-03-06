package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.DriveMode;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.commands.claw.AutoCollectCubeWide;
import org.usfirst.frc.team1987.robot.commands.claw.EjectAndJiggle;
import org.usfirst.frc.team1987.robot.commands.claw.TeleCollectStowed;
import org.usfirst.frc.team1987.robot.commands.claw.WristStow;
import org.usfirst.frc.team1987.robot.commands.drive.DriveDistance;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePivot;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeightInstant;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleToLeftSwitch extends CommandGroup {

    public MiddleToLeftSwitch() {
    	addSequential(new ShiftLow());
    	addSequential(new SetElevatorHeightInstant(RobotMap.elevatorHoldCubeHeight));
    	System.out.println("left switch from middle");
    	addSequential(new DrivePath(AutoPaths.leftSwitchFromMiddle, DriveMode.DRIVEPATHLOW));
    	addSequential(new EjectAndJiggle());
    	addSequential(new LeftSwitchSwoop());
    	addParallel(new SetElevatorHeightInstant(0));
    	addSequential(new DrivePivot(-36));
    	addParallel(new AutoCollectCubeWide());
    	addSequential(new DriveDistance(12));
    	addParallel(new WristStow());
    	addSequential(new DriveDistance(-6));
    	addSequential(new TeleCollectStowed());
    }
}

package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.DriveMode;
import org.usfirst.frc.team1987.robot.commands.claw.AutoCollectCubeWide;
import org.usfirst.frc.team1987.robot.commands.claw.EjectCube;
import org.usfirst.frc.team1987.robot.commands.drive.DriveDistance;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePivot;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightToLeftScaleLeftSwitch extends CommandGroup {

    public RightToLeftScaleLeftSwitch() {
        addSequential(new RightToLeftScale());		
        addSequential(new ShiftLow());
        addSequential(new DrivePivot(132));
        addSequential(new ShiftHigh());
        addParallel(new AutoCollectCubeWide());
    	System.out.println("straightForMeterAndSomeMore");
        addSequential(new DrivePath(AutoPaths.straightForMeterAndSomeMore, DriveMode.DRIVEPATHSTRAIGHT));
        addParallel(new DriveDistance(-10));						
        addSequential(new SetElevatorHeight(12.0));
        addSequential(new DriveDistance(10));
        addSequential(new EjectCube());
    }
}

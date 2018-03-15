package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.DriveMode;
import org.usfirst.frc.team1987.robot.ScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.SetScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.claw.AutoCollectCubeWide;
import org.usfirst.frc.team1987.robot.commands.claw.EjectAndJiggle;
import org.usfirst.frc.team1987.robot.commands.claw.EjectCube;
import org.usfirst.frc.team1987.robot.commands.drive.DriveDistance;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePivot;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team1987.robot.commands.elevator.GoToScaleHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeightInstant;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftToRightScaleRightSwitch extends CommandGroup {

    public LeftToRightScaleRightSwitch() {			
    	addSequential(new ShiftHigh());
    	addSequential(new SetScaleOwnership(ScaleOwnership.DISOWNED));
    	System.out.println("left to right scale");
		addSequential(new DrivePath(AutoPaths.leftToRightScale, DriveMode.DRIVEPATHTURNS));
		addSequential(new GoToScaleHeight());
		addSequential(new EjectAndJiggle());
		addSequential(new SetElevatorHeightInstant(0));			
        addSequential(new ShiftLow());
        addSequential(new DrivePivot(-127));		
        addSequential(new ShiftHigh());
        addParallel(new AutoCollectCubeWide());
    	System.out.println("straightForMeterAndSomeMore");
        addSequential(new DrivePath(AutoPaths.straightForMeterAndSomeMore, DriveMode.DRIVEPATHSTRAIGHT));
        addParallel(new DriveDistance(-2));			//needs tuning				
        addSequential(new SetElevatorHeight(12.0));				
        addSequential(new DriveDistance(5));
        addSequential(new EjectCube());
    }
}




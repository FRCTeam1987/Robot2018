package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.DriveMode;
import org.usfirst.frc.team1987.robot.RobotMap;
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
        addSequential(new DrivePivot(144));		//was 132
        addSequential(new ShiftHigh());
        addParallel(new AutoCollectCubeWide());
//    	System.out.println("straightForMeterAndSomeMore");
//        addSequential(new DrivePath(AutoPaths.straightForMeterAndSomeMore, DriveMode.DRIVEPATHSTRAIGHT));
    	addSequential(new DriveDistance(50));
        addParallel(new DriveDistance(-2));				//needs tuning				
        addSequential(new SetElevatorHeight(16.0));
        addSequential(new DriveDistance(5));			//needs tuning
        addSequential(new EjectCube(RobotMap.weakEject));
    }
}

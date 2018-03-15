package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.DriveMode;
import org.usfirst.frc.team1987.robot.ScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.LogMessage;
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
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LeftToLeftScaleLeftSwitch extends CommandGroup {

    public LeftToLeftScaleLeftSwitch() {
    	addSequential(new ShiftHigh());
    	addSequential(new SetScaleOwnership(ScaleOwnership.DISOWNED));
    	System.out.println("Left to left scale left switch");
    	addSequential(new DrivePath(AutoPaths.leftToLeftScale, DriveMode.DRIVEPATHTURNS));
    	addSequential(new GoToScaleHeight());
        addSequential(new EjectAndJiggle());
        addParallel(new SetElevatorHeight(0));
        addSequential(new ShiftLow());
        addSequential(new DrivePivot(112));
        addSequential(new ShiftHigh());
        addParallel(new AutoCollectCubeWide());
    	System.out.println("straightForMeterAndSomeMore");
        addSequential(new DrivePath(AutoPaths.straightForMeterAndSomeMore, DriveMode.DRIVEPATHSTRAIGHT));	
        addSequential(new LogMessage("Begin drive distance back"));
        addSequential(new WaitCommand(0.1));
        addSequential(new SetElevatorHeightInstant(12.0));
        addSequential(new DriveDistance(-2));						//check this
        addSequential(new LogMessage("End drive distance back"));
        addSequential(new WaitCommand(0.1));
        addSequential(new DriveDistance(5));						//check this
        addSequential(new WaitCommand(0.1));
        addSequential(new EjectCube());
    }
}

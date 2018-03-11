package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.ScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.DisableCompressor;
import org.usfirst.frc.team1987.robot.commands.EnableCompressor;
import org.usfirst.frc.team1987.robot.commands.LogMessage;
import org.usfirst.frc.team1987.robot.commands.SetScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.claw.AutoCollectCubeWide;
import org.usfirst.frc.team1987.robot.commands.claw.EjectAndJiggle;
import org.usfirst.frc.team1987.robot.commands.claw.EjectCube;
import org.usfirst.frc.team1987.robot.commands.drive.DriveAScosh;
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
public class LeftToLeftScaleLeftSwitch extends CommandGroup {

    public LeftToLeftScaleLeftSwitch() {
    	addSequential(new DisableCompressor());
    	addSequential(new SetScaleOwnership(ScaleOwnership.DISOWNED));
    	addSequential(new DrivePath(AutoPaths.leftToLeftScale));
    	addSequential(new GoToScaleHeight());
        addSequential(new EjectAndJiggle());
        addParallel(new SetElevatorHeight(0));
        addSequential(new ShiftLow());
        addSequential(new DrivePivot(112));
        addSequential(new ShiftHigh());
//        addSequential(new LogMessage("Begin drive & collect parallel"));
        addParallel(new AutoCollectCubeWide());
        addSequential(new DrivePath(AutoPaths.straightForMeterAndSomeMore));
        addSequential(new LogMessage("Begin drive a scosh back"));
        addSequential(new SetElevatorHeightInstant(12.0));
        addSequential(new DriveAScosh(-10));
        addSequential(new LogMessage("End drive a scosh back"));
//        addSequential(new DriveAScosh(10));						//TODO: Change to a drive straight for a distance
//        addSequential(new EjectCube());
//        addSequential(new EnableCompressor());
    }
}

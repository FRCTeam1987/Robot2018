package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.DriveMode;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePivot;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftToLeftScaleRightSwitch extends CommandGroup {

    public LeftToLeftScaleRightSwitch() {
       addSequential(new ShiftHigh());
       addSequential(new LeftToLeftScale());
       addSequential(new ShiftLow());
       addSequential(new DrivePivot(100));
       addSequential(new ShiftHigh());
   	   System.out.println("left scale to right switch");
       addSequential(new DrivePath(AutoPaths.leftScaleToRightSwitch, DriveMode.DRIVEPATHTURNS));
    }
}

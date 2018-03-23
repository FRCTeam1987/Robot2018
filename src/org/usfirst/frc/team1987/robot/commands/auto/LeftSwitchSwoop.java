package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.DriveMode;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSwitchSwoop extends CommandGroup {

    public LeftSwitchSwoop() {
       addSequential(new ShiftLow());
       addSequential(new DrivePath(AutoPaths.leftSwitchSwoop, DriveMode.DRIVEPATHLOW, true));
    }
}

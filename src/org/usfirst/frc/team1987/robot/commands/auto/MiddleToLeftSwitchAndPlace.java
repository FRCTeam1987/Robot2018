package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.commands.DisableCompressor;
import org.usfirst.frc.team1987.robot.commands.EnableCompressor;
import org.usfirst.frc.team1987.robot.commands.claw.EjectCube;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleToLeftSwitchAndPlace extends CommandGroup {

    public MiddleToLeftSwitchAndPlace() {
    	addSequential(new DisableCompressor());
    	addSequential(new DrivePath(AutoPaths.leftSwitchFromMiddle));
    	addSequential(new EjectCube());
    	addSequential(new EnableCompressor());
    }
}

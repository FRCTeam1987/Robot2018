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
public class MiddleToLeftSwitch extends CommandGroup {

    public MiddleToLeftSwitch() {
//    	addSequential(new DisableCompressor());
    	addSequential(new DrivePath(AutoPaths.leftSwitchFromMiddle));
//    	addSequential(new EjectCube());
//    	addSequential(new EnableCompressor());
    }
}

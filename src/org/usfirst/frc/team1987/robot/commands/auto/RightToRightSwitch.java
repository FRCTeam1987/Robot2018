package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.ScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.DisableCompressor;
import org.usfirst.frc.team1987.robot.commands.EnableCompressor;
import org.usfirst.frc.team1987.robot.commands.SetScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.claw.EjectCube;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.elevator.GoToScaleHeight;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RightToRightSwitch extends CommandGroup {

    public RightToRightSwitch() {
    	addSequential(new DisableCompressor());
        addSequential(new DrivePath(AutoPaths.rightToRightSwitch));
        addSequential(new EjectCube());
    	addSequential(new EnableCompressor());
    }
}
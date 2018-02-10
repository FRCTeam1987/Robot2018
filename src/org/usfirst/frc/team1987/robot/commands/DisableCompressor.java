package org.usfirst.frc.team1987.robot.commands;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class DisableCompressor extends InstantCommand {

    public DisableCompressor() {
        super();
    }

    protected void initialize() {
    	Robot.compressor.setClosedLoopControl(false);
		Robot.compressor.stop();
    }
}

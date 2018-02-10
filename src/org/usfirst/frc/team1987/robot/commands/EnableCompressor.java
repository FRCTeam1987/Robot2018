package org.usfirst.frc.team1987.robot.commands;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class EnableCompressor extends InstantCommand {

    public EnableCompressor() {
        super();
    }

    protected void initialize() {
    	Robot.compressor.setClosedLoopControl(true);
    }
}

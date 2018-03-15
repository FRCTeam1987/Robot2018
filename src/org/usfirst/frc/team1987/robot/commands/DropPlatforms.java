package org.usfirst.frc.team1987.robot.commands;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class DropPlatforms extends InstantCommand {

    public DropPlatforms() {
        super();
        requires(Robot.drive);
    }

    protected void initialize() {
    	Robot.drive.dropPlatforms();
    }
}

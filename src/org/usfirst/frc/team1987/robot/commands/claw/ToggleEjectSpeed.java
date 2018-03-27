package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ToggleEjectSpeed extends InstantCommand {

    public ToggleEjectSpeed() {
        super();
        requires(Robot.claw);
    }

    protected void initialize() {
    	Robot.claw.toggleEjectSpeed();
    }
}

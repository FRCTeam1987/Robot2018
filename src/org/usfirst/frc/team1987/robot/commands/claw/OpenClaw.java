package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class OpenClaw extends InstantCommand {

    public OpenClaw() {
        super();
        requires(Robot.claw);
    }

    protected void initialize() {
    	Robot.claw.open();
    }
}

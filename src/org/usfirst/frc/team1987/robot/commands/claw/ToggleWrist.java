package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ToggleWrist extends InstantCommand {

    public ToggleWrist() {
        super();
        requires(Robot.claw);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.claw.toggleWrist();
    }

}

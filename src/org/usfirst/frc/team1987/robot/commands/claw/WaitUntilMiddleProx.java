package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitUntilMiddleProx extends Command {

	
    public WaitUntilMiddleProx() {
        requires(Robot.claw);
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Robot.claw.isCubeNear();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

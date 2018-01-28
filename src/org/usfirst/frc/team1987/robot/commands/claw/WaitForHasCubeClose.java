package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitForHasCubeClose extends Command {

	private double m_timeout;
	
    public WaitForHasCubeClose(final double timeout) {
        requires(Robot.claw);
        m_timeout = timeout;
    }

    protected void initialize() {
    	setTimeout(m_timeout);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Robot.claw.getHasCubeClose(); //added the not thingy
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

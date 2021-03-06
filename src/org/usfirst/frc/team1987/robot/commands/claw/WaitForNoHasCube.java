package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitForNoHasCube extends Command {

	private double m_timeout;
	
    public WaitForNoHasCube(final double timeout) {
        requires(Robot.claw);
        m_timeout = timeout;
        setTimeout(timeout);
    }

    protected void initialize() {
    	setTimeout(m_timeout);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return !Robot.claw.isCubeNear() || isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitForNoHasCubeFar extends Command {

	private double m_timeout;
	
    public WaitForNoHasCubeFar(final double timeout) {
        requires(Robot.claw);
       m_timeout = timeout;
    }

    protected void initialize() {
    	setTimeout(m_timeout);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return !Robot.claw.getHasCubeFar();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

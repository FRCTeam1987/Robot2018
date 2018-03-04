package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetRumble extends Command {

	final double m_power;
	
    public SetRumble(final double power) {
        requires(Robot.claw);
        m_power = power;
    }

    protected void initialize() {
    	Robot.claw.setRumble(m_power);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

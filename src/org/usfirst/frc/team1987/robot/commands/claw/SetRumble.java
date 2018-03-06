package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SetRumble extends InstantCommand {

	final double m_power;
	
    public SetRumble(final double power) {
        super();
        requires(Robot.claw);
        m_power = power;
    }

    protected void initialize() {
    	Robot.claw.setRumble(m_power);
    }
}

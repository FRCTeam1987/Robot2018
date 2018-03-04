package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SetEjectStrength extends InstantCommand {

	final boolean m_isStrongEject;
	
    public SetEjectStrength(final boolean isStrongEject) {
        super();
        requires(Robot.claw);
        m_isStrongEject = isStrongEject;
    }

    protected void initialize() {
        Robot.claw.setEjectSpeed(m_isStrongEject);

    }
}

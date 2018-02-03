package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */

/*
public class SetClawActuator extends InstantCommand {

	private boolean m_position;
	
    public SetClawActuator(final boolean position) {
        super();
        
        requires(Robot.claw);
        m_position = position;
    }

    protected void initialize() {
    	Robot.claw.setActuator(m_position);
    }

}

*/

public class SetClawActuator extends InstantCommand {
	enum POSITION { OPEN, CLOSED };
	private POSITION m_position;

	public SetClawActuator(POSITION position) {
		m_position = position;
	}

	protected void initialize() {
		Robot.claw.setActuator(m_position == POSITION.OPEN);
	}
}
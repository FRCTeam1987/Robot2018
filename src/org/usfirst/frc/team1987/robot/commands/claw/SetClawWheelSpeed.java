package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SetClawWheelSpeed extends InstantCommand {

	private final double m_voltagePercentage;
	
    public SetClawWheelSpeed(final double voltagePercentage) {
        super();
        requires(Robot.claw);
        m_voltagePercentage = voltagePercentage;
    }

    protected void initialize() {
    	Robot.claw.setWheels(m_voltagePercentage, m_voltagePercentage);
    }
}

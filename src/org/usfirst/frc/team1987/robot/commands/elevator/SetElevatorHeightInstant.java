package org.usfirst.frc.team1987.robot.commands.elevator;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SetElevatorHeightInstant extends InstantCommand {

	private double mInches;
	
    public SetElevatorHeightInstant(final double inches) {
        super();
        requires(Robot.elevator);
		mInches = inches;
    }

    protected void initialize() {
    	Robot.elevator.setElevatorAbsolute(mInches);
    }
}

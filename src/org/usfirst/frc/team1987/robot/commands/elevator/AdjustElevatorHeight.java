package org.usfirst.frc.team1987.robot.commands.elevator;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AdjustElevatorHeight extends Command {

	private double mInches;
	
    public AdjustElevatorHeight(final double inches) {
        requires(Robot.elevator);
        mInches = inches;
        setTimeout(1.0);
    }

    protected void initialize() {
    	Robot.elevator.setElevatorRelative(mInches);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Robot.elevator.isWithinTolerance() || isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

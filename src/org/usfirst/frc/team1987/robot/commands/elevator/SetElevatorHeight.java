package org.usfirst.frc.team1987.robot.commands.elevator;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.util.Util;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetElevatorHeight extends Command {

	private double mInches;
	
	public SetElevatorHeight(final double inches) {
		requires(Robot.elevator);
		mInches = inches;
		setTimeout(1.0);
	}
	
    public SetElevatorHeight(final double inches, final double timeout) {
        requires(Robot.elevator);
        mInches = inches;
        setTimeout(timeout);
    }

    protected void initialize() {
    	Robot.elevator.setElevatorAbsolute(mInches);
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

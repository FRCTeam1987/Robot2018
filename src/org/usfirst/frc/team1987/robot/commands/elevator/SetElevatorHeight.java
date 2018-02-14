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
    }

    protected void initialize() {
    	Robot.elevator.setElevatorAbsolute(mInches);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Robot.elevator.isWithinTolerance();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

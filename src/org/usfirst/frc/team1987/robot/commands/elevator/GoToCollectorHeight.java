package org.usfirst.frc.team1987.robot.commands.elevator;

import org.usfirst.frc.team1987.robot.CollectorHeight;
import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GoToCollectorHeight extends Command {

    public GoToCollectorHeight() {
        requires(Robot.elevator);
        setTimeout(1.0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	final CollectorHeight collectorHeight = Robot.getCollectorHeight();
    	switch(collectorHeight) {
    		case FLOOR:
    			Robot.elevator.setElevatorAbsolute(RobotMap.elevatorFloorCubePyramidHeight);
    			break;
    		case HOLD:
    			Robot.elevator.setElevatorAbsolute(RobotMap.elevatorHoldCubeHeight);
    		case MIDDLE:
    			Robot.elevator.setElevatorAbsolute(RobotMap.elevatorMiddleCubePyramidHeight);
    			break;
    		case TOP:
    			Robot.elevator.setElevatorAbsolute(RobotMap.elevatorTopCubePyramidHeight);
    			break;
    	}
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

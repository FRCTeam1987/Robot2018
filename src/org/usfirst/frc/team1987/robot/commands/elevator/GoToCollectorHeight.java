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
    		case MIDDLE:
    			Robot.elevator.setElevatorAbsolute(RobotMap.elevatorMiddleCubePyramidHeight);
    			break;
    		case TOP:
    			Robot.elevator.setElevatorAbsolute(RobotMap.elevatorTopCubePyramidHeight);
    			break;
    		case FLOOR:
    			Robot.elevator.setElevatorAbsolute(RobotMap.elevatorFloorCubePyramidHeight);
    	}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.elevator.isWithinTolerance() || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

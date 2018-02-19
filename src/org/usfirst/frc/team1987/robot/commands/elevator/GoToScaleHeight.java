package org.usfirst.frc.team1987.robot.commands.elevator;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.ScaleOwnership;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GoToScaleHeight extends Command {

    public GoToScaleHeight() {
        requires(Robot.elevator);
        setTimeout(1.0);	//1.0
    }

    protected void initialize() {
    	final ScaleOwnership ownership = Robot.getScaleOwnership();
    	switch(ownership) {
    		case DISOWNED:
    			Robot.elevator.setElevatorAbsolute(RobotMap.disownedScaleHeight);
    			break;
    		case NEUTRAL:
    			Robot.elevator.setElevatorAbsolute(RobotMap.neutralScaleHeight);
    			break;
    		case OWNED:
    			Robot.elevator.setElevatorAbsolute(RobotMap.ownedScaleHeight);
    			break;
    		case DISOWNED_WORST:
    			Robot.elevator.setElevatorAbsolute(RobotMap.disownedWorstScaleHeight);
    			break;
    	}
    	
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return /*Robot.elevator.isWithinTolerance() ||*/ isTimedOut();
    }

    protected void end() {
    }
    
    protected void interrupted() {
    }
}

package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleEjectCube extends Command {

    public TeleEjectCube() {
        requires(Robot.claw);
        setTimeout(0.5);
    }

    protected void initialize() {
    	Robot.claw.open();
    	if(Robot.claw.isStrongEject() == false) {
    		Robot.claw.setWheels(RobotMap.weakEject, RobotMap.weakEject);
    	}
    	else {
    		Robot.claw.setWheels(RobotMap.strongEject, RobotMap.strongEject);
    	}
    }
    
    protected void execute() {
    	
    }
    
    protected boolean isFinished() {
    	return isTimedOut();
    }
    
    protected void end() {
		Robot.claw.setWheels(0, 0);
    }
    
    protected void interrupted() {
		Robot.claw.setWheels(0, 0);
    }
}

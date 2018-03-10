package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleopEjectCube extends Command {

    public TeleopEjectCube() {
    	requires(Robot.claw);
        setTimeout(0.25);
    }

    protected void initialize() {
    	if(Robot.claw.isStrongEject() == false)
    		Robot.claw.setWheels(RobotMap.weakEject, RobotMap.weakEject);
    	else
    		Robot.claw.setWheels(RobotMap.strongEject, RobotMap.strongEject);
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

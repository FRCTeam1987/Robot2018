package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EjectAndJiggle extends Command {

	private double initialTime = 0;
	private final double startEjectWait = 0.05;
	private boolean hasStartedEjecting = false;
	
    public EjectAndJiggle() {
       requires(Robot.claw);
       setTimeout(0.2);
    }

    protected void initialize() {
    	Robot.claw.wristDown();
//    	Robot.claw.setWheels(0.9, 0.9);
    	initialTime = Timer.getFPGATimestamp();
    	hasStartedEjecting = false;
    }

    protected void execute() {
    	if(hasStartedEjecting == false && Timer.getFPGATimestamp() - initialTime > startEjectWait) {
    		hasStartedEjecting = true;
    		Robot.claw.setWheels(0.6, 0.6);
    	}
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    	Robot.claw.setWheels(0, 0);
//    	Robot.claw.setWheels(0.9, 0.9);
    	Robot.claw.wristUp();
    }

    protected void interrupted() {
    	end();
    }
}

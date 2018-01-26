package org.usfirst.frc.team1987.robot.commands.elevator;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GoToAbsolutePosition extends Command {
			
	private double m_inches;
	
    public GoToAbsolutePosition(final double inches) {
       requires(Robot.elevator);
//     setTimeout(2);
       m_inches = inches;
    }

    protected void initialize() {
//    	Robot.elevator.zeroElevatorEncoder(); 	//only used for testing purposes   	
    }

    protected void execute() {
    	Robot.elevator.setElevatorAbsolute(m_inches);
    }

    protected boolean isFinished() {
        return true;	
    }

    protected void end() {
    }

    
    protected void interrupted() {
    }
}

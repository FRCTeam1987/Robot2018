package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AdjustCubeInClaw extends Command {
	
	private double m_rightPercent;
	private double m_leftPercent;
	
    public AdjustCubeInClaw(final double timeout, final double rightPercent, final double leftPercent) {
       requires(Robot.claw);
       m_rightPercent = rightPercent;
       m_leftPercent = leftPercent;
    }

    protected void initialize() {
    	Robot.claw.setWheels(m_leftPercent, m_rightPercent);
    }

    protected void execute() {
		if (Robot.claw.isCubeNear()) {	//try with open claw in else
			Robot.claw.close();
			System.out.println("Fingers Closed");
		}
		
		if (Robot.claw.isRightLimitSwitchTriggered() && !Robot.claw.isLeftLimitSwitchTriggered()) {
			Robot.claw.setWheels(m_leftPercent, 0.1 * -m_rightPercent);
		}
		else if(Robot.claw.isLeftLimitSwitchTriggered() && !Robot.claw.isRightLimitSwitchTriggered()) {
			Robot.claw.setWheels(0.1 * -m_leftPercent, m_rightPercent);
		}
		else {
	    	Robot.claw.setWheels(m_leftPercent, m_rightPercent);
    	}
    }

    protected boolean isFinished() {
        return Robot.claw.isCubeNear() && Robot.claw.isLeftLimitSwitchTriggered() && Robot.claw.isRightLimitSwitchTriggered();	//use debounced values for isFinished?
    }

    protected void end() {
    	Robot.claw.setWheels(0.0, 0.0);
    	Robot.claw.close();
    }

    protected void interrupted() {
    	System.out.println("Interrupted!");
    	end();
    }
}

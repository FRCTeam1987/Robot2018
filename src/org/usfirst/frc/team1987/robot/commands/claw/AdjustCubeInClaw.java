package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AdjustCubeInClaw extends Command {
	
	private double m_timeout;
	private double m_rightPercent;
	private double m_leftPercent;
	
    public AdjustCubeInClaw(final double timeout, final double rightPercent, final double leftPercent) {
       requires(Robot.claw);
       m_timeout = timeout;
       m_rightPercent = rightPercent;
       m_leftPercent = leftPercent;
    }

    protected void initialize() {
    	setTimeout(m_timeout);
    	Robot.claw.setWheels(m_leftPercent, m_rightPercent);
    }

    protected void execute() {
    	if (Robot.claw.isCubeNear()) {
    		Robot.claw.close();
    	}
    	
    	if (Robot.claw.getRightLimitSwitch() && !Robot.claw.getLeftLimitSwitch()) {
    		Robot.claw.setWheels(m_leftPercent, 0.5 * -m_rightPercent);
    	}
    	else if(Robot.claw.getLeftLimitSwitch() && !Robot.claw.getRightLimitSwitch()) {
    		Robot.claw.setWheels(0.5 * -m_leftPercent, m_rightPercent);
    	}
    	else {
        	Robot.claw.setWheels(m_leftPercent, m_rightPercent);
    	}
    }

    protected boolean isFinished() {
        return Robot.claw.isCubeNear() && Robot.claw.getLeftLimitSwitch() && Robot.claw.getRightLimitSwitch();
    }

    protected void end() {
    	Robot.claw.setWheels(0.0, 0.0);
    }

    protected void interrupted() {
    	Robot.claw.setWheels(0.0, 0.0);
    }
}

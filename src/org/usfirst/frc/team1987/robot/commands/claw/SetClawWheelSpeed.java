package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SetClawWheelSpeed extends InstantCommand {

	private final double m_voltagePercentage;
	
    public SetClawWheelSpeed() {
    	super();
    	m_voltagePercentage = 0;
    }
    
    public SetClawWheelSpeed(final double voltagePercentage) {
        super();
        requires(Robot.claw);
        m_voltagePercentage = voltagePercentage;
    }

    protected void initialize() {
    	double percentage = m_voltagePercentage;
//    	if(percentage == 0) {
//    		percentage = Robot.claw.isStrongEject() ? RobotMap.strongEject : RobotMap.weakEject;
//    	}
    	Robot.claw.setWheels(percentage, percentage);
    }
}

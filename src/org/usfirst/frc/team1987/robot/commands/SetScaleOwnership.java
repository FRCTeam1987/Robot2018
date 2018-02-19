package org.usfirst.frc.team1987.robot.commands;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.ScaleOwnership;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SetScaleOwnership extends InstantCommand {

	public final ScaleOwnership m_scaleOwnership;
	
    public SetScaleOwnership(ScaleOwnership scaleOwnership) {
        super();
        m_scaleOwnership = scaleOwnership;
    }

    protected void initialize() {
    	Robot.setScaleOwnership(m_scaleOwnership);
    }

}

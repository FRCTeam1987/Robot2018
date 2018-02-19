package org.usfirst.frc.team1987.robot.commands;

import org.usfirst.frc.team1987.robot.CollectorHeight;
import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SetPotentialCollectorHeight extends InstantCommand {

	public final CollectorHeight m_collectorHeight;
	
    public SetPotentialCollectorHeight(CollectorHeight collectorHeight) {
        super();
        m_collectorHeight = collectorHeight;
        
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.setCollectorHeight(m_collectorHeight);
    }

}

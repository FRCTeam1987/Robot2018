package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class StopCollect extends InstantCommand {

    public StopCollect() {
        super();
        requires(Robot.claw);
    }

    protected void initialize() {
    	Robot.claw.setWheels(0);
    }
    
}

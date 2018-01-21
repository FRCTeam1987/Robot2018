package org.usfirst.frc.team1987.robot.commands.drive;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class XboxDrive extends Command {

    public XboxDrive() {
        requires(Robot.drive);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.drive.xboxDrive(Robot.m_oi.getXbox());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    
    protected void interrupted() {
    }
}

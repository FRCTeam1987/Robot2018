package org.usfirst.frc.team1987.robot.commands.drive;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TeleopDrive extends Command {

    public TeleopDrive() {
        requires(Robot.drive);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.drive.xboxDrive(Robot.oi.getDriver());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    
    protected void interrupted() {
    }
}

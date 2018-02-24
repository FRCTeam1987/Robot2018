package org.usfirst.frc.team1987.robot.commands.drive;

import org.usfirst.frc.team1987.robot.DriveMode;
import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.util.Util;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivePivot extends Command {
	
	
	
    public DrivePivot() {
        requires(Robot.drive);
        
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	return true;
    }

    protected void end() {
    	Robot.drive.tankDrive(0.0, 0.0);
    }

    protected void interrupted() {
    	Robot.drive.tankDrive(0.0, 0.0);
    }
}

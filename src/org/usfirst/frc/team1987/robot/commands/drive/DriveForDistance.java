package org.usfirst.frc.team1987.robot.commands.drive;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForDistance extends Command {

	double m_leftTargetInches;
	double m_rightTargetInches;
	final double currentLeftInches = Robot.drive.getLeftEncoderDistance();
	final double currentRightInches = Robot.drive.getRightEncoderDistance();
	final double tolerance;
	
    public DriveForDistance(double leftTargetInches, double rightTargetInches) {
        requires(Robot.drive);
    	m_leftTargetInches = leftTargetInches;
    	m_rightTargetInches = rightTargetInches;  
    	tolerance = 0.25;
    }

    protected void initialize() {
    	m_leftTargetInches += currentLeftInches;
    	m_rightTargetInches += currentRightInches;
    	
    	Robot.drive.setLeftMasterForDistance(m_leftTargetInches);
    	Robot.drive.setRightMasterForDistance(m_rightTargetInches);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Math.abs(Robot.drive.getLeftEncoderDistance()) < tolerance 
        		&& Math.abs(Robot.drive.getRightEncoderDistance()) < tolerance;
    }

    protected void end() {
    	Robot.drive.tankDrive(0.0, 0.0);
    }

    protected void interrupted() {
    	Robot.drive.tankDrive(0.0, 0.0);
    }
}

package org.usfirst.frc.team1987.robot.commands.drive;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForDistance extends Command {

	private final double m_leftTargetInches;
	private final double m_rightTargetInches;
	private final double tolerance;
	
    public DriveForDistance(double leftTargetInches, double rightTargetInches) {
        requires(Robot.drive);
    	m_leftTargetInches = leftTargetInches;
    	m_rightTargetInches = rightTargetInches;  
    	tolerance = 0.25;
    }

    protected void initialize() {
    	double currentLeftInches = Robot.drive.getLeftEncoderDistance();
    	double currentRightInches = Robot.drive.getRightEncoderDistance();
    	double desiredLeftInches = currentLeftInches + m_leftTargetInches;
    	double desiredRightInches = currentRightInches + m_rightTargetInches;
    	
    	Robot.drive.setLeftMasterForDistance(desiredLeftInches);
    	Robot.drive.setRightMasterForDistance(desiredRightInches);
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

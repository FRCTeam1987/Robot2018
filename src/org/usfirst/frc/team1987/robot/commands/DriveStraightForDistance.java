package org.usfirst.frc.team1987.robot.commands;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightForDistance extends Command {

	private final double mTargetDistance;
	private double mTargetHeading;
	private double mCurrentHeading;
	
    public DriveStraightForDistance(final double targetDistance) {
        requires(Robot.drive);
        setTimeout(2);
        mTargetDistance = targetDistance;
        mTargetHeading = 0;
    }

    protected void initialize() {
    	Robot.drive.zeroDriveEncoders();
    	Robot.drive.zeroHeading();
    }

    protected void execute() {
    	if (mTargetDistance > 0)
    		Robot.drive.tankDrive(0.8, 0.8);
    	else if (mTargetDistance < 0)
    		Robot.drive.tankDrive(-0.8, -0.8);
    	
    	mCurrentHeading = Robot.drive.getHeading();
    	
    	if (mCurrentHeading - mTargetHeading > 1)
    		Robot.drive.tankDrive(.75, .9);
    	else if (mCurrentHeading - mTargetHeading < -1)
    		Robot.drive.tankDrive(.9, .75);
    }

    protected boolean isFinished() {
        return Math.abs(mTargetDistance) - Math.abs(Robot.drive.getLeftEncoderDistance()) < 1
        		&& Math.abs(mTargetDistance) - Math.abs(Robot.drive.getRightEncoderDistance()) < 1;
    }

    protected void end() {
    	Robot.drive.tankDrive(0, 0);
    }

    protected void interrupted() {
    	Robot.drive.tankDrive(0, 0);
    }
}

package org.usfirst.frc.team1987.robot.commands.drive;

import java.io.File;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

/**
 *
 */
public class DrivePath extends Command {

	private final EncoderFollower leftFollower;
	private final EncoderFollower rightFollower;
	
    public DrivePath(final String leftTrajectoryFileName, final String rightTrajectoryFileName) {
        requires(Robot.drive);
        
        File leftTrajectoryFile = new File(leftTrajectoryFileName);
        File rightTrajectoryFile = new File(rightTrajectoryFileName);
        Trajectory leftTrajectory = Pathfinder.readFromCSV(leftTrajectoryFile);
        Trajectory rightTrajectory = Pathfinder.readFromCSV(rightTrajectoryFile);
        
        leftFollower = new EncoderFollower(leftTrajectory);
        rightFollower = new EncoderFollower(rightTrajectory);
    }

    protected void initialize() {
    	Robot.drive.zeroDriveEncoders();
    	
    	leftFollower.reset();
    	leftFollower.configureEncoder(Robot.drive.getLeftRawEncoderPosition(), (int)RobotMap.ticksPerRotation, RobotMap.wheelDiameter);
    	leftFollower.configurePIDVA(RobotMap.drivePathP, RobotMap.drivePathI, RobotMap.drivePathD, RobotMap.drivePathV, RobotMap.drivePathA);
    	
    	rightFollower.reset();
    	rightFollower.configureEncoder(Robot.drive.getLeftRawEncoderPosition(), (int)RobotMap.ticksPerRotation, RobotMap.wheelDiameter);
    	rightFollower.configurePIDVA(RobotMap.drivePathP, RobotMap.drivePathI, RobotMap.drivePathD, RobotMap.drivePathV, RobotMap.drivePathA);
    }

    protected void execute() {
    	final double leftOutput = leftFollower.calculate(Robot.drive.getLeftRawEncoderPosition()) / Robot.pdp.getVoltage();
    	final double rightOutput = leftFollower.calculate(Robot.drive.getLeftRawEncoderPosition()) / Robot.pdp.getVoltage();
    	final double currentHeading = Robot.drive.getHeading();
    	final double desiredHeading = Pathfinder.r2d(leftFollower.getHeading());
    	final double headingDifference = Pathfinder.boundHalfDegrees(desiredHeading - currentHeading);
    	final double turn = calculateTurnEasing(headingDifference);
    	
    	final double absLeft = Math.abs(leftOutput);
    	final double leftAdjusted = Math.max(absLeft, RobotMap.minimumTrajectoryPercentage);	
    	Math.copySign(leftAdjusted, leftOutput);
    	
    	final double absRight = Math.abs(rightOutput);
    	final double rightAdjusted = Math.max(absRight, RobotMap.minimumTrajectoryPercentage);	
    	Math.copySign(rightAdjusted, rightOutput);
    	
    	Robot.drive.tankDrive(leftAdjusted + turn, rightAdjusted - turn);
    }

    protected boolean isFinished() {
        return leftFollower.isFinished() && rightFollower.isFinished();
    }

    protected void end() {
    	Robot.drive.tankDrive(0, 0);
    }

    protected void interrupted() {
    	Robot.drive.tankDrive(0, 0);
    }
    
    private double calculateTurnEasing(final double headingDifference) {
    	final double headingP = 0.85;
    	final double headingScalar = -1.0 / 70.0;
    	return headingP * headingScalar * headingDifference;
    }
}

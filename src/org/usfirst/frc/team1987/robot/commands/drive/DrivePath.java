package org.usfirst.frc.team1987.robot.commands.drive;

import java.io.File;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;

import com.ctre.phoenix.ErrorCode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;

/**
 *
 */
public class DrivePath extends Command {

	private final EncoderFollower leftFollower;
	private final EncoderFollower rightFollower;
	
	public DrivePath(final Waypoint[] path ) {
        requires(Robot.drive);

		EncoderFollower[] followers = Robot.drive.pathSetup(path);
		this.leftFollower = followers[0];
		this.rightFollower = followers[1];
	}
	
    public DrivePath(final String leftTrajectoryFileName, final String rightTrajectoryFileName) {
        requires(Robot.drive);
        
    
        File leftTrajectoryFile = new File(leftTrajectoryFileName);
        File rightTrajectoryFile = new File(rightTrajectoryFileName);

//        try {
//        	leftTrajectoryFile.exists();
//        }
//        catch(Exception e) {
////        	final ErrorCode leftTrajectoryFileNotFound = SmartDashboard.putString("Left Trajectory File Status", "File Not Found!");
//        	SmartDashboard.putString("Left Trajectory File Status", "File Not Found!");
//        }
//        
//        File rightTrajectoryFile = new File(rightTrajectoryFileName);
//        
//        try {
//        	rightTrajectoryFile.exists();
//        }
//        catch(Exception e) {
//        	SmartDashboard.putString("Right Trajectory File Status", "File Not Found!");
//        }
        
        Trajectory leftTrajectory = Pathfinder.readFromCSV(leftTrajectoryFile);
        Trajectory rightTrajectory = Pathfinder.readFromCSV(rightTrajectoryFile);
        
        leftFollower = new EncoderFollower(leftTrajectory);
        rightFollower = new EncoderFollower(rightTrajectory);
        
       
    }

    protected void initialize() {
        Robot.drive.zeroDriveEncoders();
    	Robot.drive.ahrsReset();
//    	leftFollower.reset();
//    	leftFollower.configureEncoder(Robot.drive.getLeftRawEncoderPosition(), (int)RobotMap.ticksPerRotation, RobotMap.wheelDiameter);
//    	leftFollower.configurePIDVA(RobotMap.drivePathP, RobotMap.drivePathI, RobotMap.drivePathD, RobotMap.drivePathV, RobotMap.drivePathA);
//    	
//    	rightFollower.reset();
//    	rightFollower.configureEncoder(Robot.drive.getLeftRawEncoderPosition(), (int)RobotMap.ticksPerRotation, RobotMap.wheelDiameter);
//    	rightFollower.configurePIDVA(RobotMap.drivePathP, RobotMap.drivePathI, RobotMap.drivePathD, RobotMap.drivePathV, RobotMap.drivePathA);

        Robot.drive.pathFollow(leftFollower, rightFollower, false);
    }

    protected void execute() {
        Robot.drive.pathFollow(leftFollower, rightFollower, false);
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
    
//    private double calculateTurnEasing(final double headingDifference) {
//    	final double headingP = 0.85;
//    	final double headingScalar = -1.0 / 70.0;
//    	return headingP * headingScalar * headingDifference;
//    }
}

package org.usfirst.frc.team1987.robot.commands.drive;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.subsystems.Drive;

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
	
	public DrivePath(final Waypoint[] path) {
        requires(Robot.drive);

		EncoderFollower[] followers = Robot.drive.pathSetup(makeTrajectory(path));
		this.leftFollower = followers[0];
		this.rightFollower = followers[1];
	}
	
	protected Trajectory makeTrajectory(final Waypoint[] path) {
		String hash = WaypointsHash(path);
		File cacheFile = new File(cacheFilename(hash));
		if (cacheFile.exists()) {
			// load the trajectory from the cache
			System.out.println("Reading cached trajectory");
			return Pathfinder.readFromFile(cacheFile);
		}
		else {
			// this path isn't cached - generate it first
	        Trajectory.Config cfg = new Trajectory.Config(Trajectory.FitMethod.HERMITE_QUINTIC, Trajectory.Config.SAMPLES_HIGH,
	                Drive.DrivetrainProfiling.dt, Drive.DrivetrainProfiling.max_velocity, Drive.DrivetrainProfiling.max_acceleration, Drive.DrivetrainProfiling.max_jerk);
	        Trajectory toFollow = Pathfinder.generate(path, cfg);
	        
	        // Cache the trajectory for next time
	        Pathfinder.writeToFile(cacheFile, toFollow);
	        System.out.println("Saving new trajectory cache");
	        return toFollow;
		}
	}
	
	protected static String WaypointsHash(Waypoint[] waypoints) {
		try {
			String str = "";
			for (int i=0; i<waypoints.length; i++) {
				str = str.concat(String.format("%.2f %.2f %.2f\n",
						waypoints[i].x, waypoints[i].y, waypoints[i].angle));
			}

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());
			return DatatypeConverter.printHexBinary(md.digest()).toUpperCase();
		}
		catch(NoSuchAlgorithmException e) {
			return "";
		}
	}
	
	protected static String cacheFilename(final String hash) {
		return "/home/lvuser/paths/".concat(hash);
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

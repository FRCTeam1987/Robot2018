package org.usfirst.frc.team1987.robot.commands.drive;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.usfirst.frc.team1987.robot.DriveMode;
import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.subsystems.Drive;
import org.usfirst.frc.team1987.util.DriveProfile;

//import com.ctre.phoenix.ErrorCode;

import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
	private boolean isBrake;
	private boolean isHighGear;
	private DriveProfile driveProfile;
	private final DriveMode driveMode;
	private boolean isReversed;
	
	public DrivePath(final Waypoint[] path, final DriveMode driveMode) {
        requires(Robot.drive);

        isBrake = false;
        isHighGear = true;
        isReversed = false;
        
		this.driveMode = driveMode;
		System.out.println("=======================");
		System.out.println("=======================");
		System.out.println("=======================");
		System.out.println(this.driveMode);
		System.out.println("=======================");
		System.out.println("=======================");
		System.out.println("=======================");
        
		EncoderFollower[] followers = Robot.drive.pathSetup(makeTrajectory(path));
		this.leftFollower = followers[0];
		this.rightFollower = followers[1];
	}
	
	public DrivePath(final Waypoint[] path, final DriveMode driveMode, final boolean isReversed) {
		this(path, driveMode);
		this.isReversed = isReversed;
	}
	
	protected Trajectory makeTrajectory(final Waypoint[] path) {
		String hash = WaypointsHash(path);
		System.out.println(hash);
		File cacheFile = new File(cacheFilename(hash));
		if(cacheFile.exists()) {
			// load the trajectory from the cache
			System.out.println("Reading cached trajectory");
			return Pathfinder.readFromFile(cacheFile);
		}
		else {
			// this path isn't cached - generate it first
			System.out.println("Creating new trajectory...");
			if(this.driveMode == DriveMode.DRIVEPATHLOW) {
				Drive.DrivetrainProfiling.setProfile(Drive.low);
				System.out.println("drive profile low");
			}
			else if(this.driveMode == DriveMode.DRIVEPATHSTRAIGHT) {
				Drive.DrivetrainProfiling.setProfile(Drive.straight);
				System.out.println("drive profile straight");
			}
			else if(this.driveMode == DriveMode.DRIVEPATHTURNS) {
				Drive.DrivetrainProfiling.setProfile(Drive.turns);
				System.out.println("drive profile turns");
			} else {
				System.out.println("=======================");
				System.out.println("=======================");
				System.out.println("=======================");
				System.out.println("did not find drive profile: " + this.driveMode);
				System.out.println("=======================");
				System.out.println("=======================");
				System.out.println("=======================");
			}
			
//			Drive.DrivetrainProfiling.setProfile(Drive.straight);
			System.out.println("profile max acceleration: " + Drive.DrivetrainProfiling.max_acceleration);
			
	        Trajectory.Config cfg = new Trajectory.Config(Trajectory.FitMethod.HERMITE_QUINTIC, Trajectory.Config.SAMPLES_HIGH,
//	                RobotMap.period, driveProfile.getMaxVelocity(), driveProfile.getMaxAcceleration(), driveProfile.getMaxJerk());
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
    	
    	leftFollower.reset();
    	rightFollower.reset();

        Robot.drive.pathFollow(leftFollower, rightFollower, isReversed); //false for forward
    }

    protected void execute() {
        Robot.drive.pathFollow(leftFollower, rightFollower, isReversed); //false for forward
    }

    protected boolean isFinished() {
        return leftFollower.isFinished() && rightFollower.isFinished();
    }

    protected void end() {
    	Robot.drive.tankDrive(0, 0);
    	
    	if(!isBrake)
    		Robot.drive.setCoast();
    	
    	if(isHighGear)
    		Robot.drive.setHighGear();
    	
    	System.out.println("Drive Path ended!");
    }

    protected void interrupted() {
    	System.out.println("Drive Path interrupted!");
    	end();
    }
}

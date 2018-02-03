package org.usfirst.frc.team1987.robot.commands.drive;

import java.io.File;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

/**
 *
 */
public class DrivePath extends Command {

	private final EncoderFollower leftFollower;
	private final EncoderFollower rightFollower;
	
    public DrivePath(final Waypoint[] waypoints) {
    	requires(Robot.drive);
    	Trajectory.Config config = new Trajectory.Config(
    			Trajectory.FitMethod.HERMITE_CUBIC, 
    			Trajectory.Config.SAMPLES_HIGH, 
    			RobotMap.period, 
    			RobotMap.maxVelocity, 
    			RobotMap.maxAcceleration, 
    			RobotMap.maxJerk
    	);
    	
    	Trajectory trajectory = Pathfinder.generate(waypoints, config);
    	TankModifier modifier = new TankModifier(trajectory).modify(RobotMap.driveWheelbaseWidth);
    	leftFollower = new EncoderFollower(modifier.getLeftTrajectory());
    	rightFollower = new EncoderFollower(modifier.getRightTrajectory());	
    }

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
    	Robot.drive.zeroYaw();
    	leftFollower.reset();
    	leftFollower.configureEncoder(Robot.drive.getLeftRawEncoderPosistion(), RobotMap.ctreMagEncoderTicksPerRevolution, RobotMap.wheelDiameter / 12.0);
    	leftFollower.configurePIDVA(RobotMap.drivePathP, RobotMap.drivePathI, RobotMap.drivePathD, RobotMap.drivePathV, RobotMap.drivePathA);
    	rightFollower.reset();
    	rightFollower.configureEncoder(Robot.drive.getRightRawEncoderPosition(), RobotMap.ctreMagEncoderTicksPerRevolution, RobotMap.wheelDiameter / 12.0);
    	rightFollower.configurePIDVA(RobotMap.drivePathP, RobotMap.drivePathI, RobotMap.drivePathD, RobotMap.drivePathV, RobotMap.drivePathA);
    }

    protected void execute() {
    	final double leftOutput = leftFollower.calculate(Robot.drive.getLeftRawEncoderPosistion()) / Robot.pdp.getVoltage();
    	final double rightOutput = rightFollower.calculate(Robot.drive.getRightRawEncoderPosition()) / Robot.pdp.getVoltage(); 
    	final double currentHeading = Robot.drive.getHeading();
    	final double desiredHeading = Pathfinder.r2d(leftFollower.getHeading()); 	
    	final double headingDifference = Pathfinder.boundHalfDegrees(desiredHeading - currentHeading);
    	final double turn = 0.85 * (-1.0 / 70.0) * headingDifference;
    	
    	final double absLeft = Math.abs(leftOutput);
    	final double leftAdjusted = Math.max(absLeft, 0.55);
    	Math.copySign(leftAdjusted, leftOutput);
    	final double absRight = Math.abs(rightOutput);
    	final double rightAdjusted = Math.max(absRight, 0.55);
    	Math.copySign(rightAdjusted, rightOutput);
    	Robot.drive.tankDrive(leftAdjusted + turn, rightAdjusted - turn);
    	
    	
//    	Robot.drive.tankDrive(leftOutput + turn, rightOutput - turn);
    	
    	SmartDashboard.putNumber("turn value", turn);
    	SmartDashboard.putNumber("Right output", rightOutput);
    	SmartDashboard.putNumber("Left Output", leftOutput);
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
}

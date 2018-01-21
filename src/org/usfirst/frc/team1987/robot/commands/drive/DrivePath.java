package org.usfirst.frc.team1987.robot.commands.drive;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;

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

    protected void initialize() {
    	Robot.drive.zeroDriveEncoders();
    	Robot.drive.zeroYaw();
    	leftFollower.reset();
    	leftFollower.configureEncoder(Robot.drive.getLeftRawEncoderPosistion(), RobotMap.ctreMagEncoderTicksPerRevolution, RobotMap.wheelDiameter * 0.0254);
    	leftFollower.configurePIDVA(RobotMap.drivePathP, RobotMap.drivePathI, RobotMap.drivePathD, RobotMap.drivePathV, RobotMap.drivePathA);
    	rightFollower.reset();
    	rightFollower.configureEncoder(Robot.drive.getRightRawEncoderPosition(), RobotMap.ctreMagEncoderTicksPerRevolution, RobotMap.wheelDiameter * 0.0254);
    	rightFollower.configurePIDVA(RobotMap.drivePathP, RobotMap.drivePathI, RobotMap.drivePathD, RobotMap.drivePathV, RobotMap.drivePathA);
    }

    protected void execute() {
    	final double leftOutput = leftFollower.calculate(Robot.drive.getLeftRawEncoderPosistion()); 
    	final double rightOutput = rightFollower.calculate(Robot.drive.getRightRawEncoderPosition()); 
    	final double currentAngle = Robot.drive.getAngle();
    	final double desiredAngle = Pathfinder.r2d(leftFollower.getHeading()); 	
    	final double angleDifference = Pathfinder.boundHalfDegrees(desiredAngle - currentAngle);
    	final double turn = 0.8 * (-1.0 / 80.0) * angleDifference;
    	Robot.drive.tankDrive(leftOutput + turn,  rightOutput - turn);
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

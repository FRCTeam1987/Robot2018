package org.usfirst.frc.team1987.robot.commands.drive;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.util.Util;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivePivot extends Command {
	
	private final double angleOffset;
	private double initialAngle;
	private double targetAngle;
	private boolean wasPreviouslyBrake;
	private final double tolerance = 1.0;
	private final double kp = 0.02;
	private final double kd = 0.025;
	
    public DrivePivot(final double angleOffset) {
        requires(Robot.drive);
        
        this.angleOffset = angleOffset;
        targetAngle = 0;
        initialAngle = 0;
        wasPreviouslyBrake = false;
        
        setTimeout(2.0);
    }

    protected void initialize() {
    	initialAngle = Robot.drive.getAngle();
    	targetAngle = initialAngle + angleOffset;
    	wasPreviouslyBrake = Robot.drive.isBrake();
    	
    	Robot.drive.setBrake();
    }

    protected void execute() {
    	final double currentAngle = Robot.drive.getAngle();
    	final double gyroRate = Robot.drive.getGyroRate();
    	final double deltaAngle = targetAngle - currentAngle;
    	double turn = Util.limit(kp * deltaAngle + kd * gyroRate);
    	
    	turn = Math.copySign(Math.max(Math.abs(turn), Math.abs(0.16)),turn);
    	
    	if(Util.isWithinTolerance(currentAngle, targetAngle, tolerance + 7)) {
    		if(Math.abs(gyroRate) < 0.025) {
    			turn = Math.copySign(Math.abs(turn) + 0.04, turn);
    		} else if (Math.abs(gyroRate) > 0.15) {
    			turn = Math.copySign(Math.abs(turn) - 0.04, turn);
    		}
       	}
    	Robot.drive.set(ControlMode.PercentOutput, turn, turn);
    }

    protected boolean isFinished() {
    	return Util.isWithinTolerance(Robot.drive.getAngle(), targetAngle, tolerance) && 
    		   Util.isWithinTolerance(Robot.drive.getGyroRate(), 0, 0.1) || 
    		   isTimedOut();
    }

    protected void end() {
    	Robot.drive.set(ControlMode.PercentOutput, 0.0, 0.0);
    	if(!wasPreviouslyBrake) {
    		Robot.drive.setCoast();
    	}
    }

    protected void interrupted() {
    	end();
    }
}

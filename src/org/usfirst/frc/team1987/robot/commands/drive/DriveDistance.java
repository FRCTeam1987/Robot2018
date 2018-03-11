package org.usfirst.frc.team1987.robot.commands.drive;

import org.usfirst.frc.team1987.robot.DriveMode;
import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.util.Util;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistance extends Command {

	private final double targetDistance;
	private final double tolerance = 1.0;
	private boolean isBrake;
	private boolean isHighGear;
	
    public DriveDistance(final double targetDistance) {
        requires(Robot.drive);
        this.targetDistance = targetDistance;
        isBrake = false;
        isHighGear = true;       
    }

    protected void initialize() {
        isBrake = Robot.drive.isBrake();
        isHighGear = Robot.drive.isHighGear();
    	
    	Robot.drive.zeroDriveEncoders();
    	Robot.drive.setLowGear();
    	Robot.drive.setBrake();
    	Robot.drive.setPID(DriveMode.STRAIGHT);
    	final double distanceTicks = Util.distanceToTicks(targetDistance, RobotMap.wheelDiameter);
    	System.out.println("distance ticks: " + distanceTicks);
    	System.out.println("left ticks - initial:" + Robot.drive.getLeftRawEncoderPosition());
    	System.out.println("right ticks - initial:" + Robot.drive.getRightRawEncoderPosition());
    	Robot.drive.set(ControlMode.Position, distanceTicks, -distanceTicks);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Util.isWithinTolerance(Math.abs(Robot.drive.getLeftEncoderDistance()), Math.abs(targetDistance), tolerance);
    }

    protected void end() {
    	if(!isBrake)
    		Robot.drive.setCoast();
    	
    	if(isHighGear)
    		Robot.drive.setHighGear();
    	
    	Robot.drive.set(ControlMode.PercentOutput, 0.0, 0.0);
    }

    protected void interrupted() {
    	end();
    }
}

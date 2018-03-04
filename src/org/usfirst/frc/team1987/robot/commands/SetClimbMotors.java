package org.usfirst.frc.team1987.robot.commands;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.util.Util;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetClimbMotors extends Command {

    public SetClimbMotors() {
        requires(Robot.drive);
        requires(Robot.elevator);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.drive.tankDrive(0.9, 0.9);
    	Robot.elevator.setElevatorAbsolute(RobotMap.climbHeight);
    }

    protected boolean isFinished() {
        return Util.isWithinTolerance(Robot.elevator.getDistance(), RobotMap.climbHeight, Util.ticksToDistance(RobotMap.elevatorToleranceInTicks, RobotMap.winchDiameter));
    }

    protected void end() {
    	Robot.drive.tankDrive(0.0, 0.0);
    }

    protected void interrupted() {
    	Robot.drive.tankDrive(0.0, 0.0);
    }
}

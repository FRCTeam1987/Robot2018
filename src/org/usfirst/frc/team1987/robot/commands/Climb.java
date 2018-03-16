package org.usfirst.frc.team1987.robot.commands;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.util.Util;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Climb extends Command {

    public Climb() {
        requires(Robot.drive);
        requires(Robot.elevator);
        setTimeout(1.0);
    }

    protected void initialize() {
//    	Robot.elevator.engageRatchet();
    	Robot.drive.ptoEngage();
    	Robot.elevator.setElevatorAbsolute(RobotMap.climbHeight);
    }

    protected void execute() {
    	Robot.drive.tankDrive(0.5, 0.5); //check values
    }

    protected boolean isFinished() {
        return Util.isWithinTolerance(Robot.elevator.getDistance(), RobotMap.climbHeight, 2) || isTimedOut(); //put 2 in RobotMap
    }

    protected void end() {
    	Robot.drive.tankDrive(0.0, 0.0);
    }

    protected void interrupted() {
    	Robot.drive.tankDrive(0.0, 0.0);
    }
}

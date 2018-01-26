package org.usfirst.frc.team1987.robot.commands.elevator;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetZeroHome extends Command {

    public SetZeroHome() {
        requires(Robot.elevator);
    }

    protected void initialize() {
    	Robot.elevator.zeroElevatorEncoder();
    }
    
    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

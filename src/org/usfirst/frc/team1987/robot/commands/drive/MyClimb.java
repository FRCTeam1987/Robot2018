package org.usfirst.frc.team1987.robot.commands.drive;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.util.Util;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MyClimb extends Command {

    public MyClimb() {
        requires(Robot.drive);
        requires(Robot.elevator);
    }

    protected void initialize() {
    	Robot.elevator.setCoast();
    	Robot.compressor.setClosedLoopControl(false);
    	Robot.compressor.stop();
    	Robot.drive.ptoEngage();
    	Robot.drive.dropDownOmniBackLower();
    	Robot.drive.dropDownOmniFrontLower();
//    	Robot.elevator.setElevatorAbsolute(0);
    	Robot.elevator.set(ControlMode.PercentOutput, 0);
    }

    protected void execute() {
    	Robot.drive.set(ControlMode.PercentOutput, 0.0, 1.0);
    }

    protected boolean isFinished() {
        return Util.isWithinTolerance(Robot.elevator.getDistance(), 0, 2.0) ;
    }

    protected void end() {
    	Robot.compressor.setClosedLoopControl(true);
    	Robot.drive.set(ControlMode.PercentOutput, 0, 0);
    	System.out.println("myClimb end");
    }

    protected void interrupted() {
    	end();
    	System.out.println("myClimb interrupted");
    }
}

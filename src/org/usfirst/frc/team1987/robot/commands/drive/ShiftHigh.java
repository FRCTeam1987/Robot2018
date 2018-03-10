package org.usfirst.frc.team1987.robot.commands.drive;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ShiftHigh extends InstantCommand {

    public ShiftHigh() {
        super();
        requires(Robot.drive);
    }

    protected void initialize() {
    	Robot.drive.dropDownOmniBackRaise();
    	Robot.drive.dropDownOmniFrontRaise();
    	Robot.drive.setHighGear();
    }

}

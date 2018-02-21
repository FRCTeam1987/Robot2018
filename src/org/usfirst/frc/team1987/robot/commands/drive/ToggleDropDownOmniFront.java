package org.usfirst.frc.team1987.robot.commands.drive;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ToggleDropDownOmniFront extends InstantCommand {

    public ToggleDropDownOmniFront() {
        super();
        requires(Robot.drive);
    }

    // Called once when the command executes
    protected void initialize() {
        Robot.drive.dropDownOmniFrontToggle();
    }

}

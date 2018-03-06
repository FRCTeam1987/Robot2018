package org.usfirst.frc.team1987.robot.commands.drive;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ToggleDropDownOmniBack extends InstantCommand {

    public ToggleDropDownOmniBack() {
        super();
        requires(Robot.drive);
    }

    protected void initialize() {
        Robot.drive.dropDownOmniBackToggle();
    }
}

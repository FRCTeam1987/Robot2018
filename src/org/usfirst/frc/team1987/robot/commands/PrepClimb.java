package org.usfirst.frc.team1987.robot.commands;

import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.commands.claw.OpenClaw;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PrepClimb extends CommandGroup {

    public PrepClimb() {
        addSequential(new OpenClaw());
        addSequential(new SetElevatorHeight(RobotMap.rungHeight));
    }
}

package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PortalCollect extends CommandGroup {

    public PortalCollect() {
        addSequential(new WristStow());
        addSequential(new SetElevatorHeight(0));
        addSequential(new TeleCollectStowed());
        
    }
}

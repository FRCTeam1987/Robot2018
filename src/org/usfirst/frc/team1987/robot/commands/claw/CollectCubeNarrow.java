package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CollectCubeNarrow extends CommandGroup {

    public CollectCubeNarrow() {
    	
    	addSequential(new SetClawActuator(false));
    	addSequential(new WaitForHasCubeFar(8.0));
    	addSequential(new SetClawWheelSpeed(0.7));
    	addSequential(new WaitForHasCubeClose(8.0));
    	addSequential(new SetClawWheelSpeed(0.0));
    }
}

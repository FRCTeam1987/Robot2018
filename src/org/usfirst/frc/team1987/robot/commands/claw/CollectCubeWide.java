package org.usfirst.frc.team1987.robot.commands.claw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CollectCubeWide extends CommandGroup {

    public CollectCubeWide() {
        
    	addSequential(new SetClawActuator(true));
    	addSequential(new SetClawWheelSpeed(0.7));
    	addSequential(new WaitForHasCubeFar(8.0));
    	addSequential(new SetClawActuator(false));
    	addSequential(new WaitForHasCubeClose(6.0));
//    	addSequential(new SetClawActuator(false));
    	addSequential(new SetClawWheelSpeed(0.0));
    }
}

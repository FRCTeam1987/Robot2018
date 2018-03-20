package org.usfirst.frc.team1987.robot.commands.claw;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class EjectCube extends CommandGroup {

    public EjectCube() {
    	addSequential(new SetClawWheelSpeed(0.9));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new SetClawWheelSpeed(0.0));
    }
    
    public EjectCube(final double speed) {
    	addSequential(new SetClawWheelSpeed(speed));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new SetClawWheelSpeed(0.0));
    }
}

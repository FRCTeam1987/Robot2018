package org.usfirst.frc.team1987.robot.commands.claw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class EjectCube extends CommandGroup {

    public EjectCube() {

    	addSequential(new SetClawWheelSpeed(-0.9));
    	addSequential(new WaitForNoHasCube(6.0));
    	addSequential(new SetClawWheelSpeed(0.0));
    }
}

package org.usfirst.frc.team1987.robot.commands.claw;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class stuff extends CommandGroup {

    public stuff() {
    	addSequential(new WristDeploy());
    	addSequential(new WaitCommand(0.25));
    	addParallel(new EjectCube());
    	addSequential(new WristStow());
    }
}

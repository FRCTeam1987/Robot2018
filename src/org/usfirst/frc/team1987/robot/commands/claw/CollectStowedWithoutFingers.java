package org.usfirst.frc.team1987.robot.commands.claw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CollectStowedWithoutFingers extends CommandGroup {

    public CollectStowedWithoutFingers() {
    	 addSequential(new AdjustCubeInClaw(5.0, -0.8, -0.8));
         addSequential(new CloseClaw());
         addSequential(new SetClawWheelSpeed(0.0));
    }
}

package org.usfirst.frc.team1987.robot.commands.claw;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoCollectCubeWide extends CommandGroup {

    public AutoCollectCubeWide() {
    	addSequential(new WristDeploy());
    	addSequential(new OpenClaw());
        addSequential(new AdjustCubeInClaw(5.0, -0.55, -0.55));
        addSequential(new CloseClaw());
        addSequential(new SetClawWheelSpeed(0.0));
        addSequential(new WristStow());
    }
}

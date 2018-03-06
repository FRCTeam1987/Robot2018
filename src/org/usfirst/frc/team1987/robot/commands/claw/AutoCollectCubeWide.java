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
        addSequential(new AdjustCubeInClaw(5.0, -0.7, -0.7));
        addSequential(new CloseClaw());
        addSequential(new SetClawWheelSpeed(0.0));
//        addSequential(new WristStow());	//took this out for DopLeftFarScale NEED TO TEST
    }
}

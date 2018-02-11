package org.usfirst.frc.team1987.robot.commands.claw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CollectCubeWide extends CommandGroup {

    public CollectCubeWide() {
        addSequential(new OpenClaw());
        addSequential(new SetClawWheelSpeed(-0.7));
        addSequential(new WaitForHasCube(5.0));
        addSequential(new CloseClaw());
        addSequential(new WaitForIsCubeNear(5.0));
        addSequential(new SetClawWheelSpeed(0.0));
    }
}

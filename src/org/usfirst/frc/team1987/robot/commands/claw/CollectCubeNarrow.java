package org.usfirst.frc.team1987.robot.commands.claw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CollectCubeNarrow extends CommandGroup {

    public CollectCubeNarrow() {
        addSequential(new CloseClaw());
        addSequential(new SetClawWheelSpeed(-0.7));
        addSequential(new WaitForIsCubeNear(8.0));
        addSequential(new SetClawWheelSpeed(0.0));
    }
}

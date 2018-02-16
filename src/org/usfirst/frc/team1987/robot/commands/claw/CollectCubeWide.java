package org.usfirst.frc.team1987.robot.commands.claw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CollectCubeWide extends CommandGroup {

    public CollectCubeWide() {
        addSequential(new OpenClaw());
        addSequential(new AdjustCubeInClaw(5.0, 0.6, 0.6));
        addSequential(new CloseClaw());
        addSequential(new SetClawWheelSpeed(0.0));
    }
}

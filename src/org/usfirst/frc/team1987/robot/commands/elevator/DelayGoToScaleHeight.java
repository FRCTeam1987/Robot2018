package org.usfirst.frc.team1987.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DelayGoToScaleHeight extends CommandGroup {
	
    public DelayGoToScaleHeight(final double waitTime) {
        addSequential(new WaitCommand(waitTime));
        addSequential(new GoToScaleHeight());
    }
}

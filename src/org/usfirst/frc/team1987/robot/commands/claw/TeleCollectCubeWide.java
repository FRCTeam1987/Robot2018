package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.commands.elevator.GoToCollectorHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;


/**
 *
 */
public class TeleCollectCubeWide extends CommandGroup {

    public TeleCollectCubeWide() {
        addSequential(new GoToCollectorHeight());
    	addSequential(new WristDeploy());
//    	addSequential(new OpenClaw());
        addSequential(new AdjustCubeInClaw(5.0, -0.7, -0.7));
        addSequential(new SetRumble(1.0));
        addSequential(new CloseClaw());
        addSequential(new SetClawWheelSpeed(0.0));
        addSequential(new WristStow());
        addSequential(new WaitCommand(1));
        addSequential(new SetRumble(0.0));
    }
}

package org.usfirst.frc.team1987.robot.commands.claw;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCollectCubeWide extends CommandGroup {

    public AutoCollectCubeWide() {
    	addSequential(new WristDeploy());
    	addSequential(new OpenClaw());
        addSequential(new AdjustCubeInClaw(5.0, -1.0, -1.0));
        addSequential(new CloseClaw());
        addSequential(new SetClawWheelSpeed(0.0));
    }
    
    protected void interrupted() {
    	System.out.println("Interrupted: AutoCollectCubeWide !!");
    }
}

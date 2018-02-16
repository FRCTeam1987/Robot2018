package org.usfirst.frc.team1987.robot.commands.claw;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CollectCubeWide extends CommandGroup {

    public CollectCubeWide() {
        SmartDashboard.putString("Command status", "Command in process");
        addSequential(new OpenClaw());
        addSequential(new AdjustCubeInClaw(5.0, -0.55, -0.55));
        addSequential(new CloseClaw());
        addSequential(new SetClawWheelSpeed(0.0));
        
        SmartDashboard.putString("Command status", "Command ended");
    }
}

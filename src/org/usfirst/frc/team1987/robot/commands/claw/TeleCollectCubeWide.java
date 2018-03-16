package org.usfirst.frc.team1987.robot.commands.claw;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.commands.elevator.GoToCollectorHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;


/**
 *
 */
public class TeleCollectCubeWide extends CommandGroup {

    public TeleCollectCubeWide() {
        addSequential(new GoToCollectorHeight());
    	addSequential(new WristDeploy());
        addSequential(new AdjustCubeInClaw(5.0, -0.8, -0.8));
        addSequential(new SetRumble(1.0));
        addSequential(new CloseClaw());
        addSequential(new SetClawWheelSpeed(0.0));
        addSequential(new WristStow());
        addSequential(new WaitCommand(1.25));
        addSequential(new SetElevatorHeight(RobotMap.elevatorHoldCubeHeight));
//        addSequential(new WaitCommand(0.5));
        addSequential(new SetRumble(0.0));
    }
    
    protected void interrupted() {
    	Robot.claw.setRumble(0.0);
    }
}

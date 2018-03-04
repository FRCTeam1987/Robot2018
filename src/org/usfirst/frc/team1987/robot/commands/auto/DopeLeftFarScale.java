package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.CollectorHeight;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.ScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.SetPotentialCollectorHeight;
import org.usfirst.frc.team1987.robot.commands.SetScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.claw.AdjustCubeInClaw;
import org.usfirst.frc.team1987.robot.commands.claw.AutoCollectCubeWide;
import org.usfirst.frc.team1987.robot.commands.claw.EjectAndJiggle;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePivot;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team1987.robot.commands.elevator.GoToScaleHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DopeLeftFarScale extends CommandGroup {

    public DopeLeftFarScale() {
    	addSequential(new SetScaleOwnership(ScaleOwnership.DISOWNED));
		addSequential(new DrivePath(AutoPaths.toFarScale));
		addSequential(new GoToScaleHeight());
		addSequential(new EjectAndJiggle());
        addSequential(new SetElevatorHeight(0));
        addSequential(new ShiftLow());
        addSequential(new DrivePivot(-132));
        addSequential(new ShiftHigh());
        addParallel(new AutoCollectCubeWide());
        addSequential(new DrivePath(AutoPaths.straightForMeterAndSome));
        addSequential(new DrivePath(AutoPaths.backupAScosh));
//        addParallel(new SetElevatorHeight(RobotMap.elevatorHoldCubeHeight));
//        addSequential(new EjectAndJiggle());
    }
}

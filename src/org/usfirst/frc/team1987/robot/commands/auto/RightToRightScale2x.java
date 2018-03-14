package org.usfirst.frc.team1987.robot.commands.auto;

import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.ScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.SetScaleOwnership;
import org.usfirst.frc.team1987.robot.commands.claw.AutoCollectCubeWide;
import org.usfirst.frc.team1987.robot.commands.claw.EjectAndJiggle;
import org.usfirst.frc.team1987.robot.commands.claw.WristStow;
import org.usfirst.frc.team1987.robot.commands.drive.DriveDistance;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePath;
import org.usfirst.frc.team1987.robot.commands.drive.DrivePivot;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftHigh;
import org.usfirst.frc.team1987.robot.commands.drive.ShiftLow;
import org.usfirst.frc.team1987.robot.commands.drive.ToggleDropDownOmniBack;
import org.usfirst.frc.team1987.robot.commands.elevator.GoToScaleHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team1987.robot.commands.elevator.SetElevatorHeightInstant;
import org.usfirst.frc.team1987.util.AutoPaths;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RightToRightScale2x extends CommandGroup {

    public RightToRightScale2x() {
    	addSequential(new ToggleDropDownOmniBack());
        addSequential(new RightToRightScale());
        addSequential(new SetScaleOwnership(ScaleOwnership.NEUTRAL));
        addSequential(new ShiftLow());
        addSequential(new DrivePivot(-112));
        addSequential(new ShiftHigh());
        addParallel(new AutoCollectCubeWide());
        addSequential(new DrivePath(AutoPaths.straightForMeterAndASkosh));	//changed this
        addSequential(new WaitCommand(0.075));
        addSequential(new DriveDistance(-3));
        addSequential(new SetElevatorHeightInstant(RobotMap.elevatorHoldCubeHeight));
        addSequential(new WristStow());
        addSequential(new DrivePivot(130));
        addSequential(new DriveDistance(24));	// was 24
        addSequential(new GoToScaleHeight());
        addSequential(new EjectAndJiggle());
        addSequential(new SetElevatorHeight(0));
    }
}

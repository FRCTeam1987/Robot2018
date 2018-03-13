package org.usfirst.frc.team1987.robot.commands.elevator;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.ScaleOwnership;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class GoToScaleHeightInstant extends InstantCommand {

    public GoToScaleHeightInstant() {
        requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        final ScaleOwnership ownership = Robot.getScaleOwnership();
        switch(ownership) {
        case DISOWNED:
            Robot.elevator.setElevatorAbsolute(RobotMap.disownedScaleHeight);
            break;
		case NEUTRAL:
			Robot.elevator.setElevatorAbsolute(RobotMap.neutralScaleHeight);
			break;
		case OWNED:
			Robot.elevator.setElevatorAbsolute(RobotMap.ownedScaleHeight);
			break;
		case DISOWNED_WORST:
			Robot.elevator.setElevatorAbsolute(RobotMap.disownedWorstScaleHeight);
			break;
		}
    }
}

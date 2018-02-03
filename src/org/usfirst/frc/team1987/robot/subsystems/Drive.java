package org.usfirst.frc.team1987.robot.subsystems;

import org.usfirst.frc.team1987.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {

	private final WPI_TalonSRX leftMaster;
	private final WPI_TalonSRX leftSlave1;
	private final WPI_TalonSRX leftSlave2;
	private final WPI_TalonSRX rightMaster;
	private final WPI_TalonSRX rightSlave1;
	private final WPI_TalonSRX rightSlave2;
	
	public Drive() {
		leftMaster = new WPI_TalonSRX(RobotMap.leftMasterID);
		leftSlave1 = new WPI_TalonSRX(RobotMap.leftSlave1ID);
		leftSlave2 = new WPI_TalonSRX(RobotMap.leftSlave2ID);
		rightMaster = new WPI_TalonSRX(RobotMap.rightMasterID);
		rightSlave1 = new WPI_TalonSRX(RobotMap.rightSlave1ID);
		rightSlave2 = new WPI_TalonSRX(RobotMap.rightSlave2ID);
		
		leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10);
		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		rightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10);
		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);

		leftSlave1.follow(leftMaster);
		leftSlave2.follow(leftMaster);
		rightSlave1.follow(rightMaster);
		rightSlave2.follow(rightMaster);	
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


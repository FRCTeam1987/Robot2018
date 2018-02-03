package org.usfirst.frc.team1987.robot.subsystems;

import org.usfirst.frc.team1987.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

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
	private final DifferentialDrive robotDrive;
	
	public Drive() {
		leftMaster = new WPI_TalonSRX(RobotMap.leftMasterID);
		leftSlave1 = new WPI_TalonSRX(RobotMap.leftSlave1ID);
		leftSlave2 = new WPI_TalonSRX(RobotMap.leftSlave2ID);
		rightMaster = new WPI_TalonSRX(RobotMap.rightMasterID);
		rightSlave1 = new WPI_TalonSRX(RobotMap.rightSlave1ID);
		rightSlave2 = new WPI_TalonSRX(RobotMap.rightSlave2ID);
		robotDrive = new DifferentialDrive(leftMaster, rightMaster); 
		
//		leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10);
//		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		leftMaster.configPeakOutputForward(1, 0);
		leftMaster.configPeakOutputReverse(-1, 0);
		leftMaster.configNominalOutputForward(0.0, 0);
		leftMaster.configNominalOutputReverse(0.0, 0);
		leftMaster.setNeutralMode(NeutralMode.Brake);
//		rightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10);
//		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
//		rightMaster.setSensorPhase(true);
		rightMaster.configPeakOutputForward(1, 0);
		rightMaster.configPeakOutputReverse(-1, 0);
		rightMaster.configNominalOutputForward(0.0, 0);
		rightMaster.configNominalOutputReverse(0.0, 0);
		rightMaster.setNeutralMode(NeutralMode.Brake);

		leftSlave1.follow(leftMaster);
		leftSlave2.follow(leftMaster);
		rightSlave1.follow(rightMaster);
		rightSlave2.follow(rightMaster);
		
		robotDrive.setSafetyEnabled(false);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


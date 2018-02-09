package org.usfirst.frc.team1987.robot.subsystems;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.commands.XboxDrive;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;

import org.broncobots.util.Util;

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
	public final AHRS ahrs;
	
	public Drive() {
		ahrs = new AHRS(SPI.Port.kMXP);
		leftMaster = new WPI_TalonSRX(RobotMap.leftMasterID);
		leftSlave1 = new WPI_TalonSRX(RobotMap.leftSlave1ID);
		leftSlave2 = new WPI_TalonSRX(RobotMap.leftSlave2ID);
		rightMaster = new WPI_TalonSRX(RobotMap.rightMasterID);
		rightSlave1 = new WPI_TalonSRX(RobotMap.rightSlave1ID);
		rightSlave2 = new WPI_TalonSRX(RobotMap.rightSlave2ID);
		robotDrive = new DifferentialDrive(leftMaster, rightMaster);
		
		leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, RobotMap.defaultTimeout);
		final ErrorCode leftEncoderErrorCode = leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.drivePIDIDX, RobotMap.defaultTimeout);
		if (leftEncoderErrorCode != ErrorCode.OK) {
			SmartDashboard.putString("Left Drive Encoder Status", leftEncoderErrorCode.toString());
		}
		leftMaster.configPeakOutputForward(1, 0);
		leftMaster.configPeakOutputReverse(-1, 0);
		leftMaster.configNominalOutputForward(0.0, 0);
		leftMaster.configNominalOutputReverse(0.0, 0);
		leftMaster.setNeutralMode(NeutralMode.Brake);
		rightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, RobotMap.defaultTimeout);
		final ErrorCode rightEncoderErrorCode = rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.drivePIDIDX, RobotMap.defaultTimeout);
		if (rightEncoderErrorCode != ErrorCode.OK) {
			SmartDashboard.putString("Right Drive Encoder Status", rightEncoderErrorCode.toString());
		}
		rightMaster.setSensorPhase(true);
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

	public void xboxDrive(XboxController xbox) {
		final double move = xbox.getTriggerAxis(Hand.kRight) - xbox.getTriggerAxis(Hand.kLeft);
		final double rotate = xbox.getX(Hand.kLeft);
		robotDrive.arcadeDrive(-move, rotate);
	}
	
	public void tankDrive(final double left, final double right) {
		robotDrive.tankDrive(left, right);
	}
	
	public void driveDistance(final double leftInches, final double rightInches) {
		double leftRotations = Util.distanceToRotations(leftInches,  RobotMap.wheelDiameter);
		double rightRotations = Util.distanceToRotations(rightInches, RobotMap.wheelDiameter);
		
		leftMaster.set(ControlMode.Position, leftRotations);
		rightMaster.set(ControlMode.Position, rightRotations);
	}
	
	public void zeroDriveEncoders() {
		leftMaster.setSelectedSensorPosition(0, RobotMap.drivePIDIDX, RobotMap.defaultTimeout);
		rightMaster.setSelectedSensorPosition(0, RobotMap.drivePIDIDX, RobotMap.defaultTimeout);
	}
	
	public int getLeftRawEncoderPosition() {
		return leftMaster.getSelectedSensorPosition(0);
	}
	
	
	public int getRightRawEncoderPosition() {
		return rightMaster.getSelectedSensorPosition(0);
	}
	
	public double getLeftEncoderDistance() {
		return Util.rotationsToDistance(Util.getCtreEncoderRotations(getLeftRawEncoderPosition()), RobotMap.wheelDiameter);
	}
	
	public double getRightEncoderDistance() {
		return Util.rotationsToDistance(Util.getCtreEncoderRotations(getRightRawEncoderPosition()), RobotMap.wheelDiameter);
	}
	
	public double getHeading() {
		return 360.0 - ahrs.getFusedHeading();
	}
	
	public void zeroHeading() {
		ahrs.zeroYaw(); 	//might need to be changed
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new XboxDrive());
    }
    
    public void periodic() {
    	SmartDashboard.putNumber("left inches", getLeftEncoderDistance());
    	SmartDashboard.putNumber("right inches", getRightEncoderDistance());  	
    	SmartDashboard.putNumber("heading", getHeading());
    }
}


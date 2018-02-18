package org.usfirst.frc.team1987.robot.subsystems;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.commands.drive.TeleopDrive;
import org.usfirst.frc.team1987.robot.commands.drive.ToggleShifter;
import org.usfirst.frc.team1987.util.Util;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;

enum DriveMode {
	PIVOT,
	STRAIGHT,
	TRAJECTORY
}

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
	private final DoubleSolenoid shifter;
	private final AHRS ahrs;
	
	public Drive() {
		ahrs = new AHRS(SPI.Port.kMXP);
		leftMaster = new WPI_TalonSRX(RobotMap.leftMasterID);
		leftSlave1 = new WPI_TalonSRX(RobotMap.leftSlave1ID);
		leftSlave2 = new WPI_TalonSRX(RobotMap.leftSlave2ID);
		rightMaster = new WPI_TalonSRX(RobotMap.rightMasterID);
		rightSlave1 = new WPI_TalonSRX(RobotMap.rightSlave1ID);
		rightSlave2 = new WPI_TalonSRX(RobotMap.rightSlave2ID);
		robotDrive = new DifferentialDrive(leftMaster, rightMaster);
		shifter = new DoubleSolenoid(RobotMap.pcmDrive, RobotMap.shifterHigh, RobotMap.shifterLow);
		
		leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, RobotMap.defaultTimeout);
		final ErrorCode leftEncoderErrorCode = leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.drivePIDIDX, RobotMap.defaultTimeout);
		if (leftEncoderErrorCode != ErrorCode.OK) {
			SmartDashboard.putString("Left Drive Encoder Status", leftEncoderErrorCode.toString());
		} 
		else if (leftEncoderErrorCode == ErrorCode.OK) {
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
		else if (rightEncoderErrorCode == ErrorCode.OK) {
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
		double move = xbox.getTriggerAxis(Hand.kRight) - xbox.getTriggerAxis(Hand.kLeft);
		double rotate = xbox.getX(Hand.kLeft);
		
		if(Robot.elevator.getDistance() > 10) {
			move *= .75;
			rotate *= .75;
		}
		robotDrive.arcadeDrive(move, rotate, true); //-move
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
	
	public void setHighGear() {
		shifter.set(Value.kForward);
	}
	
	public void setLowGear() {
		shifter.set(Value.kReverse);
	}
	
	private boolean isHighGear() {
		return shifter.get() == Value.kForward;
	}
	
	public void toggleShift() {
		if(isHighGear() == true) {
			setLowGear();
			SmartDashboard.putString("Shitter status", "low gear");
		}
		else {
			setHighGear();
			SmartDashboard.putString("Shitter status", "high gear");
		}
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
	
	public void setLeftMasterForDistance(final double leftInches) {
		final double leftTicks = Util.distanceToTicks(leftInches, RobotMap.wheelDiameter);
		leftMaster.set(ControlMode.Position, leftTicks);
	}
	
	public void setRightMasterForDistance(final double rightInches) {
		final double rightTicks = Util.distanceToRotations(rightInches, RobotMap.wheelDiameter);
		rightMaster.set(ControlMode.Position, rightTicks);
	}
	
	public void setPID(DriveMode mode) {
		double m_P;
		double m_I;
		double m_D;
		switch(mode) {
			case PIVOT: 
				if (isHighGear()) {
					m_P = RobotMap.drivePivotHighP;
					m_I = RobotMap.drivePivotHighI;
					m_D = RobotMap.drivePivotHighD;
				} else {
					m_P = RobotMap.drivePivotLowP;
					m_I = RobotMap.drivePivotLowI;
					m_D = RobotMap.drivePivotLowD;
				}
				break;
			case STRAIGHT:
				if (isHighGear()) {
					m_P = RobotMap.driveStraightHighP;
					m_I = RobotMap.driveStraightHighI;
					m_D = RobotMap.driveStraightHighD;
				} else {
					m_P = RobotMap.driveStraightLowP;
					m_I = RobotMap.driveStraightLowI;
					m_D = RobotMap.driveStraightLowD;
				}
				break; 
			default:
				return;	
		}
		
		leftMaster.config_kP(RobotMap.drivePIDIDX, m_P, RobotMap.defaultTimeout);
		leftMaster.config_kI(RobotMap.drivePIDIDX, m_I, RobotMap.defaultTimeout);
		leftMaster.config_kD(RobotMap.drivePIDIDX, m_D, RobotMap.defaultTimeout);
		
		rightMaster.config_kP(RobotMap.drivePIDIDX, m_P, RobotMap.defaultTimeout);
		rightMaster.config_kI(RobotMap.drivePIDIDX, m_I, RobotMap.defaultTimeout);
		rightMaster.config_kD(RobotMap.drivePIDIDX, m_D, RobotMap.defaultTimeout);
	}
	
	public void zeroHeading() {
		ahrs.zeroYaw(); 	//might need to be changed
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new TeleopDrive());
    }
    
    public void periodic() {
//    	SmartDashboard.putNumber("left inches", getLeftEncoderDistance());
//    	SmartDashboard.putNumber("right inches", getRightEncoderDistance());  	
    	SmartDashboard.putNumber("heading", getHeading());
    }
}


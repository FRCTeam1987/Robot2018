package org.usfirst.frc.team1987.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.util.Util;

import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 *
 */
public class Elevator extends Subsystem {

	private final WPI_TalonSRX winchMotor;
	private final DigitalInput homeSensor;
//	private final double homeInches = 0;		//values need to changed eventually, also move to constructor
//	private final double minInches = -2;		
//	private final double maxInches = 72;
   
	public Elevator() {
    	winchMotor = new WPI_TalonSRX(RobotMap.elevatorID); 
    	homeSensor = new DigitalInput(RobotMap.elevatorHomeID);
    	
//    	winchMotor.configPeakOutputForward(1, 0);
//    	winchMotor.configPeakOutputReverse(-1, 0);
//    	winchMotor.configNominalOutputForward(0, 0);
//    	winchMotor.configNominalOutputReverse(0, 0);
    	
    	winchMotor.setInverted(false);
    	
//    	winchMotor.setSafetyEnabled(false);
    	
    	winchMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, RobotMap.defaultTimeout);
    	final ErrorCode winchMotorErrorCode = winchMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.drivePIDIDX, RobotMap.defaultTimeout);
    	if(winchMotorErrorCode != ErrorCode.OK) {
    		SmartDashboard.putString("Winch motor encoder status", winchMotorErrorCode.toString());
    	}
    	else if(winchMotorErrorCode == ErrorCode.OK) {
    		SmartDashboard.putString("Winch motor encoder status", "OK");
    	}
    	winchMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.drivePIDIDX, RobotMap.defaultTimeout);
    	
//    	winchMotor.selectProfileSlot(0, 0);	//param 1 = "profile slot to select" - Example used 0 - why?
//		winchMotor.config_kF(0, 0.0, 10);	//was .05
//		winchMotor.config_kP(0, 0.3, 10);
//		winchMotor.config_kI(0, 0.0, 10);
//		winchMotor.config_kD(0, 0.0, 10);
//		winchMotor.configMotionAcceleration(40, 10); 	//param 1 = sensorUnitsPer100msPerSec - need to calc?
//		winchMotor.configMotionCruiseVelocity(20, 10);	//param 1 = sensorUnitsPer10ms - need to calc?
//		winchMotor.configAllowableClosedloopError(0, Util.distanceToTicks(0.25, 43.0), 10);
		
		zeroElevatorEncoder();
	}
	
	private boolean isAtHome() {
		return !homeSensor.get();
	}
	
	private void zeroElevatorEncoder() {
		winchMotor.setSelectedSensorPosition(0, RobotMap.drivePIDIDX, RobotMap.defaultTimeout);
	}
	
	private int getTicks() {
		return winchMotor.getSelectedSensorPosition(RobotMap.drivePIDIDX);
	}
	
	private double getDistance() {
		return getTicks() / (double)RobotMap.elevatorTicksPerInch;
	}
	
    public void initDefaultCommand() {
       
    }
    
    public void periodic() {    	
    	SmartDashboard.putNumber("Current ticks", getTicks());
    	SmartDashboard.putNumber("Current inches", getDistance());
    	SmartDashboard.putNumber("Theoretical inches", Util.rotationsToDistance(Util.getCtreEncoderRotations(getTicks()), 2.125));
    	SmartDashboard.putBoolean("home sensor", isAtHome());
    }
}


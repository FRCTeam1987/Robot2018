package org.usfirst.frc.team1987.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.util.Util;

import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 *
 */
public class Elevator extends Subsystem {

	private final WPI_TalonSRX winchMotor;
	private final DigitalInput homeSensor;
	private final double homeInches = 0;		//values need to changed eventually, also move to constructor
	private final double minInches = -2.5;
	private final int minTicks = Util.distanceToTicks(minInches, RobotMap.winchDiameter);	
	private final double maxInches = 30.875;
	private final int maxTicks = Util.distanceToTicks(maxInches, RobotMap.winchDiameter);
   
	public Elevator() {
    	winchMotor = new WPI_TalonSRX(RobotMap.elevatorID); 
    	homeSensor = new DigitalInput(RobotMap.elevatorHomeID);
    	
//    	winchMotor.configForwardSoftLimitEnable(true, RobotMap.drivePIDIDX);
//    	winchMotor.configReverseSoftLimitEnable(true, RobotMap.drivePIDIDX);
    	
    	winchMotor.configPeakOutputForward(1, 0);
    	winchMotor.configPeakOutputReverse(-1, 0);
    	winchMotor.configNominalOutputForward(0, 0);
    	winchMotor.configNominalOutputReverse(0, 0);
    	
    	winchMotor.setInverted(false);
//    	winchMotor.setSensorPhase(true);
    	winchMotor.setSafetyEnabled(false);
    	
    	winchMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, RobotMap.defaultTimeout);
    	final ErrorCode winchMotorErrorCode = winchMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.drivePIDIDX, RobotMap.defaultTimeout);
    	
    	if(winchMotorErrorCode != ErrorCode.OK) {
    		SmartDashboard.putString("Winch motor encoder status", winchMotorErrorCode.toString());
    	}
    	else if(winchMotorErrorCode == ErrorCode.OK) {
    		SmartDashboard.putString("Winch motor encoder status", "OK");
    	}
    	winchMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.drivePIDIDX, RobotMap.defaultTimeout);
    	
    	winchMotor.selectProfileSlot(0, 0);	//param 1 = "profile slot to select" - Example used 0 - why?
		winchMotor.config_kF(0, 0.0, 10);	//was .05
		winchMotor.config_kP(0, 0.2, 10);
		winchMotor.config_kI(0, 0.0, 10);
		winchMotor.config_kD(0, 0.0, 10);
//		winchMotor.configMotionAcceleration(40, 10); 	//param 1 = sensorUnitsPer100msPerSec - need to calc?
//		winchMotor.configMotionCruiseVelocity(20, 10);	//param 1 = sensorUnitsPer10ms - need to calc?
//		winchMotor.configAllowableClosedloopError(0, Util.distanceToTicks(0.25, 43.0), 10);
		
		zeroElevatorEncoder();
	}
	
	private void zeroElevatorEncoder() {
		winchMotor.setSelectedSensorPosition(0, RobotMap.drivePIDIDX, RobotMap.defaultTimeout);
	}
	
	private boolean isAtHome() {
		return !homeSensor.get();
	}
	
	private boolean isOutOfRange(final int desiredPosition) {	//in ticks
		return desiredPosition > maxTicks || desiredPosition < minTicks;
	}
	
	public boolean isWithinTolerance() {
		return Math.abs(winchMotor.getClosedLoopError(RobotMap.drivePIDIDX)) < 50;		//adjust tolerance
	}
		
	private int getTicks() {
		return winchMotor.getSelectedSensorPosition(RobotMap.drivePIDIDX);
	}
	
	private double getDistance() {
		return getTicks() / (double)RobotMap.ticksPerInch;
	}
	
	public void setElevatorAbsolute(final double inchesAbsolute) {
		final int ticksAbsolute = Util.distanceToTicks(inchesAbsolute, RobotMap.winchDiameter);
		
		SmartDashboard.putNumber("ticksAbsolute", ticksAbsolute);
		
		if(isOutOfRange(ticksAbsolute)) {
			SmartDashboard.putString("Elevator Status", "Request out of bounds");	
			return;
		}
		
		SmartDashboard.putNumber("inches absolute", Util.ticksToDistance(ticksAbsolute, RobotMap.winchDiameter));
		winchMotor.set(ControlMode.Position, ticksAbsolute);
	}
	
	public void setElevatorRelative(final double inchesRelative) {
		final double inchesAbsolute = inchesRelative + Util.ticksToDistance(winchMotor.getSelectedSensorPosition(RobotMap.drivePIDIDX), RobotMap.winchDiameter);
		
		setElevatorAbsolute(inchesAbsolute);
	}
	
    public void initDefaultCommand() {
       
    }
    
    public void periodic() {    	
    	SmartDashboard.putNumber("Current ticks", getTicks());
    	SmartDashboard.putNumber("Current inches", getDistance());
    	SmartDashboard.putNumber("Theoretical inches", Util.rotationsToDistance(Util.getCtreEncoderRotations(getTicks()), RobotMap.winchDiameter));
    	SmartDashboard.putBoolean("home sensor", isAtHome());
    	SmartDashboard.putNumber("closed loop error", winchMotor.getClosedLoopError(RobotMap.drivePIDIDX));
    	
    	if(isAtHome() == true)
    		zeroElevatorEncoder();
    		
    }
}

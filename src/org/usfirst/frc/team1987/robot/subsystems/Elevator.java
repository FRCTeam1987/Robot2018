package org.usfirst.frc.team1987.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.util.Util;

import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

/**
 *
 */
public class Elevator extends Subsystem {

	private final WPI_TalonSRX winchMotor;
	private final DigitalInput homeSensor;
//	private final Solenoid ratchet;
	private final double minInches = -2.5;
	private final int minTicks = Util.distanceToTicks(minInches, RobotMap.winchDiameter);	
	private final double maxInches = 32.25;	//30.875
	private final int maxTicks = Util.distanceToTicks(maxInches, RobotMap.winchDiameter);
	private int m_ticksAbsolute;
   
	public Elevator() {
    	winchMotor = new WPI_TalonSRX(RobotMap.elevatorID); 
    	homeSensor = new DigitalInput(RobotMap.elevatorHomeID);
//    	ratchet = new Solenoid(RobotMap.pcmOther, RobotMap.elevatorRatchet);
    	
//    	winchMotor.configForwardSoftLimitEnable(true, RobotMap.drivePIDIDX);
//    	winchMotor.configReverseSoftLimitEnable(true, RobotMap.drivePIDIDX);
    	
    	winchMotor.configPeakOutputForward(1, 0);
    	winchMotor.configPeakOutputReverse(-1, 0);
    	winchMotor.configNominalOutputForward(0, 0);
    	winchMotor.configNominalOutputReverse(0, 0);
    	
    	winchMotor.setInverted(false);
//    	winchMotor.setSensorPhase(true);
    	winchMotor.setSafetyEnabled(false);
    	winchMotor.setNeutralMode(NeutralMode.Brake);
    	
    	winchMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, RobotMap.defaultTimeout);
    	final ErrorCode winchMotorErrorCode = winchMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.drivePIDIDX, RobotMap.defaultTimeout);
    	
    	if(winchMotorErrorCode != ErrorCode.OK) {
    		SmartDashboard.putString("Winch motor encoder status", winchMotorErrorCode.toString());
    	}
    	winchMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.drivePIDIDX, RobotMap.defaultTimeout);
    	
    	winchMotor.selectProfileSlot(0, 0);	//param 1 = "profile slot to select" - Example used 0 - why?
		winchMotor.config_kF(0, 0.0, 10);	//was .05
		winchMotor.config_kP(0, 0.2, 10);
		winchMotor.config_kI(0, 0.0, 10);
		winchMotor.config_kD(0, 0.2, 10);
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
		SmartDashboard.putNumber("current tolerance in inches", Util.ticksToDistance(RobotMap.elevatorToleranceInTicks, RobotMap.winchDiameter));
		SmartDashboard.putNumber("isWithinTolerance closed loop error", winchMotor.getClosedLoopError(RobotMap.drivePIDIDX));
		return Math.abs(m_ticksAbsolute - getTicks()) < RobotMap.elevatorToleranceInTicks;		//adjust tolerance
	}
		
	private int getTicks() {
		return winchMotor.getSelectedSensorPosition(RobotMap.drivePIDIDX);
	}
	
//	private int getSetPoint() {
//		return winchMotor.getcl
//	}
//	
	public double getDistance() {
		return getTicks() / (double)RobotMap.ticksPerInch;
	}
	
	public void setElevatorAbsolute(final double inchesAbsolute) {
		final int ticksAbsolute = Util.distanceToTicks(inchesAbsolute, RobotMap.winchDiameter);
		
		SmartDashboard.putNumber("ticksAbsolute", ticksAbsolute);
		
		if(isOutOfRange(ticksAbsolute)) {
			SmartDashboard.putString("Elevator Status", "Request out of bounds");	
			return;
		}
		else {
			SmartDashboard.putString("Elevator Status", "Request acceptable");
		}
		
		SmartDashboard.putNumber("inches absolute", Util.ticksToDistance(ticksAbsolute, RobotMap.winchDiameter));
		m_ticksAbsolute = ticksAbsolute;
		winchMotor.set(ControlMode.Position, ticksAbsolute);
	}
	
	public void setElevatorRelative(final double inchesRelative) {
		final double inchesAbsolute = inchesRelative + Util.ticksToDistance(winchMotor.getSelectedSensorPosition(RobotMap.drivePIDIDX), RobotMap.winchDiameter);
		
		setElevatorAbsolute(inchesAbsolute);
	}
	
	public void set(final ControlMode controlMode, final double value) {
		winchMotor.set(controlMode, value);
	}
	
	public void setCoast() {
		winchMotor.setNeutralMode(NeutralMode.Coast);
	}
	
	public void setBrake() {
		winchMotor.setNeutralMode(NeutralMode.Brake);
	}
	
//	public void disengageRatchet() {
//		ratchet.set(false);
//	}
//	
//	public void engageRatchet() {
//		ratchet.set(true);
//	}
//	
//	public void toggleRatchet() {
//		ratchet.set(!ratchet.get());
//	}
	
    public void initDefaultCommand() {
       
    }
    
    public void periodic() {    	
//    	SmartDashboard.putNumber("Current ticks", getTicks());
//    	SmartDashboard.putNumber("Current inches", getDistance());
    	SmartDashboard.putNumber("Elevator - Inches", Util.rotationsToDistance(Util.getCtreEncoderRotations(getTicks()), RobotMap.winchDiameter));
    	SmartDashboard.putBoolean("Elevator - Home", isAtHome());
    	SmartDashboard.putNumber("Elevator - Percent", winchMotor.get());
//    	SmartDashboard.putNumber("closed loop error", winchMotor.getClosedLoopError(RobotMap.drivePIDIDX));
//    	SmartDashboard.putNumber("POV Status", Robot.oi.getDriver().getPOV());
    	
    	if(isAtHome() == true && getTicks() != 0) 
    		zeroElevatorEncoder();
    	
    		
    		
    }
}


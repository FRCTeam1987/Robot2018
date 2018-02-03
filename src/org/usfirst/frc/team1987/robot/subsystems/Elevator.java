package org.usfirst.frc.team1987.robot.subsystems;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {

	private final WPI_TalonSRX elevator;
	private final double homeInches = 0;		//values need to changed eventually, also move to constructor
	private final double minInches = -2;
	private final double maxInches = 72;
   
	public Elevator()
	{
    	elevator = new WPI_TalonSRX(RobotMap.elevatorID); 
    	
    	elevator.configPeakOutputForward(1, 0);
    	elevator.configPeakOutputReverse(-1, 0);
    	elevator.configNominalOutputForward(0, 0);
    	elevator.configNominalOutputReverse(0, 0);
    	
    	elevator.setInverted(false);
    	
//    	elevator.setSafetyEnabled(false);
    	
    	elevator.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10);
    	elevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    	
    	elevator.selectProfileSlot(0, 0);	//param 1 = "profile slot to select" - Example used 0 - why?
		elevator.config_kF(0, 0.0, 10);	//was .05
		elevator.config_kP(0, 0.3, 10);
		elevator.config_kI(0, 0.0, 10);
		elevator.config_kD(0, 0.0, 10);
//		elevator.configMotionAcceleration(40, 10); 	//param 1 = sensorUnitsPer100msPerSec - need to calc?
//		elevator.configMotionCruiseVelocity(20, 10);	//param 1 = sensorUnitsPer10ms - need to calc?
		elevator.configAllowableClosedloopError(0, inchesToTicks(0.25), 10);
		
		zeroElevatorEncoder();

	}
	
	public void zeroElevatorEncoder()
	{
//		int absolutePosition = elevator.getSensorCollection().getPulseWidthPosition();
//		
//		absolutePosition &= 0xFFF;
		
		elevator.setSelectedSensorPosition(0, 0, 10);
	}
	
	
	private double getRotations()
	{
		return elevator.getSelectedSensorPosition(0) / 4096.0;
	}
	
	public double getElevatorDistance()
	{
		return rotationsToInches(getRotations());
	}
	
	public int getRawElevatorEncoderPosition()
	{
		return elevator.getSelectedSensorPosition(0);
	}
		
	public boolean isOutOfRange(final int desiredPosition) { // in ticks
//		SmartDashboard.putNumber("Desired position", desiredPosition); //problem is here for three inches: 2586.0 <-------
		return desiredPosition > inchesToTicks(maxInches) || desiredPosition < inchesToTicks(minInches);	//return true o false
	}
	
	public void setElevatorAbsolute(final double inchesAbsolute)
	{
		final int ticksAbsolute = inchesToTicks(inchesAbsolute);
				
		if(isOutOfRange(ticksAbsolute)) {	//problem is in isInRange try taking out the exclamation mark
			SmartDashboard.putString("Elevator Status", "Request out of physical bounds");
			return;
		}
		
		SmartDashboard.putNumber("ticks absolute before motor set" , ticksAbsolute);
		SmartDashboard.putNumber("inches absolute before motor set", ticksToInches(ticksAbsolute));
		SmartDashboard.putNumber("initial position", elevator.getSelectedSensorPosition(0));
		
		elevator.set(ControlMode.Position, ticksAbsolute);	
		
//		SmartDashboard.putNumber("Target position after set", elevator.ge)
//		SmartDashboard.putNumber("Closed loop error", elevator.getClosedLoopError(0));
		SmartDashboard.putString("Elevator Status", "Acceptable Position");
	}
		
	
	public void setElevatorRelative(final double inchesRelative)
	{
		final int ticksRelative = inchesToTicks(inchesRelative);
		final int ticksAbsolute = ticksRelative + elevator.getSelectedSensorPosition(0);
				
		setElevatorAbsolute(ticksToInches(ticksAbsolute));	//is setting ticksAbsolute to 0
	}
	
	private double inchesToRotations(final double inches)
	{
		return inches / 4.75;
	}	
	
	private double rotationsToInches(final double rotations) 
	{    	
    	return rotations * 4.75;	
    }
	
	private double ticksToRotations(final int ticks)
	{
		return ticks / 4096.0;
	}
	
	private double ticksToInches(final int ticks)
	{
		return (double) ticksToRotations(ticks) * 4.75;
	}
	
	private int inchesToTicks(final double inches)
	{
		return (int) (inchesToRotations(inches) * 4096);		
	}
		 
	
	public void periodic()
	{
		SmartDashboard.putNumber("Current Inches", getElevatorDistance());
		SmartDashboard.putNumber("Current ticks", inchesToTicks(getElevatorDistance()));
		SmartDashboard.putNumber("Current rotations", getRotations());
		SmartDashboard.putNumber("Closed loop error 2.0", elevator.getClosedLoopError(0));

	}
	
	
    public void initDefaultCommand() {
        //////////////////// do later //////////////////////////
    }
}


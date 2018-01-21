package org.usfirst.frc.team1987.robot.subsystems;

import org.usfirst.frc.team1987.robot.RobotMap;

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
   
	public Elevator()
	{
    	elevator = new WPI_TalonSRX(RobotMap.elevatorID); 
    	
    	elevator.configPeakOutputForward(1, 0);
    	elevator.configPeakOutputReverse(-1, 0);
    	elevator.configNominalOutputForward(0, 0);
    	elevator.configNominalOutputReverse(0, 0);
    	
    	elevator.setSafetyEnabled(false);
    	
    	elevator.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10);
    	elevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    	
	}
	
	public void zeroElevatorEncoder()
	{
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
		
	private double rotationsToInches(final double rotations) 
	{    	
    	return rotations * 5.8125;	//conversion factor needs to be changed
    }
		
	public void periodic()
	{
		SmartDashboard.putNumber("inches", getElevatorDistance());
		SmartDashboard.putNumber("rotations", getRotations());
	}
	
    public void initDefaultCommand() {
        //////////////////// do later //////////////////////////
    }
}


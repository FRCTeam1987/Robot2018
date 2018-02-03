package org.usfirst.frc.team1987.robot.subsystems;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.commands.drive.XboxDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;


import com.kauailabs.navx.frc.AHRS;

/**
 *
 */
public class Drive extends Subsystem {

    private final WPI_TalonSRX leftMaster;
    private final WPI_TalonSRX leftSlave;
    private final WPI_TalonSRX rightMaster;
    private final WPI_TalonSRX rightSlave;
    private final DifferentialDrive robotDrive; 
    public final AHRS ahrs;
    
    public Drive()
    {
        ahrs = new AHRS(SPI.Port.kMXP); //TODO should probably check this
    	leftMaster = new WPI_TalonSRX(RobotMap.leftMasterID); 	//4
    	leftSlave = new WPI_TalonSRX(RobotMap.leftSlaveID); 	//5
    	rightMaster = new WPI_TalonSRX(RobotMap.rightMasterID); //1
    	rightSlave = new WPI_TalonSRX(RobotMap.rightSlaveID); 	//2
    	
//    	leftSlave.set(ControlMode.Follower, 1);
    	leftSlave.follow(leftMaster);
    	leftMaster.configPeakOutputForward(1, 0);
    	leftMaster.configPeakOutputReverse(-1, 0);
    	leftMaster.configNominalOutputForward(0.0, 0);
    	leftMaster.configNominalOutputReverse(0.0, 0);
    	
    	robotDrive = new DifferentialDrive(leftMaster, rightMaster);
    	robotDrive.setSafetyEnabled(false);
    	
    	leftMaster.setNeutralMode(NeutralMode.Brake);
    	
//    	leftMaster.setInverted(true);
//    	leftSlave.setInverted(true);
    	
    	leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10);
    	leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    	
//    	rightSlave.set(ControlMode.Follower, 2);
    	rightSlave.follow(rightMaster);
    	rightMaster.configPeakOutputForward(1, 0);
    	rightMaster.configPeakOutputReverse(-1, 0);
    	rightMaster.configNominalOutputForward(0.0, 0);
    	rightMaster.configNominalOutputReverse(0.0, 0);
    	
    	rightMaster.setNeutralMode(NeutralMode.Brake);
    	
//    	rightMaster.setInverted(true);
//    	rightSlave.setInverted(true);
    	
    	rightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10);
    	rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    	rightMaster.setSensorPhase(true);
    	
    }
    
    public void zeroYaw()
    {
    	ahrs.zeroYaw();
    }    	
    
    public double getYaw()
    {
    	return ahrs.getYaw();
    }
    
    public double getAngle()
    {
    	return ahrs.getAngle();
    }
    
    public double getHeading() {
    	return 360.0 - ahrs.getFusedHeading();
    }
    
    public void xboxDrive(XboxController xbox)
    {
    	final double move = xbox.getTriggerAxis(Hand.kRight) - xbox.getTriggerAxis(Hand.kLeft);
    	final double rotate = -xbox.getX(Hand.kLeft);
    	robotDrive.arcadeDrive(-move, rotate);
    }
    
    public void tankDrive(final double left, final double right)
    {
    	robotDrive.tankDrive(left, right);
    }
    
    private double rotationsToInches(final double rotations) {
    	
    	double circumference = Math.PI * RobotMap.wheelDiameter;
    	
    	return circumference * rotations;
    	
    }
    
    public void zeroDriveEncoders()
    {
    	rightMaster.setSelectedSensorPosition(0, 0, 10);
    	leftMaster.setSelectedSensorPosition(0, 0, 10);
    }
   
    public int getRightRawEncoderPosition()
    {
    	return rightMaster.getSelectedSensorPosition(0);
    }
    
    public int getLeftRawEncoderPosistion()
    {
    	return leftMaster.getSelectedSensorPosition(0);
    }
    
    public double getRightEncoderDistance()
    {

    	return rotationsToInches((rightMaster.getSelectedSensorPosition(0) / 4096.0)); 
    }

    public double getLeftEncoderDistance()
    {
    	
    	return rotationsToInches((leftMaster.getSelectedSensorPosition(0) / 4096.0)); 
    }
   
    private double inchesToRotations(final double inches) 
    {
    	return inches / (Math.PI * RobotMap.wheelDiameter);
    }
    
    private double inchesToMeters (final double inches)
    {
    	final double conversion = 0.0254;
    	return inches * conversion;
    }
    
    public void driveDistance(final double leftInches, final double rightInches) 
    {
    	double leftRotations = inchesToRotations(leftInches);
    	double rightRotations = inchesToRotations(rightInches);
    	leftMaster.set(ControlMode.Position, leftRotations);
    	rightMaster.set(ControlMode.Position, rightRotations);
    }
    
    public void periodic()
    {
    	SmartDashboard.putNumber("left inches", getLeftEncoderDistance());
    	SmartDashboard.putNumber("Right inches", getRightEncoderDistance());
    	SmartDashboard.putNumber("Yaw", getYaw());
    	SmartDashboard.putNumber("Angle", getAngle());
    	SmartDashboard.putNumber("heading", getHeading());
    	
    	SmartDashboard.putNumber("Leftmaster voltage", Robot.drive.leftMaster.getMotorOutputVoltage());
    	SmartDashboard.putNumber("Rightmaster voltage", Robot.drive.rightMaster.getMotorOutputVoltage());

    }
    
    public void initDefaultCommand() 
    {
       setDefaultCommand(new XboxDrive());
    }
    
    
}


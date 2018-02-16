package org.usfirst.frc.team1987.robot.subsystems;

import org.usfirst.frc.team1987.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Claw extends Subsystem {
	
	private final WPI_TalonSRX right;
	private final WPI_TalonSRX left;
	private final DoubleSolenoid fingers;
	private final DoubleSolenoid wrist;
	private final DigitalInput cubeProx;
	private final DigitalInput leftLimitSwitch;
	private final DigitalInput rightLimitSwitch;
	
	public Claw() {
		right = new WPI_TalonSRX(RobotMap.clawRight);
		left = new WPI_TalonSRX(RobotMap.clawLeft);
		fingers = new DoubleSolenoid(RobotMap.pcmOther, RobotMap.clawFingersOpen, RobotMap.clawFingersClosed);
		wrist = new DoubleSolenoid(RobotMap.pcmOther, RobotMap.clawWristUp, RobotMap.clawWristDown);
		cubeProx = new DigitalInput(RobotMap.clawCubeProx);
		leftLimitSwitch = new DigitalInput(RobotMap.clawLeftLimitSwitch);
		rightLimitSwitch = new DigitalInput(RobotMap.clawRightLimitSwitch);
				
		addChild(right);
		addChild(left);
		addChild(fingers);
		addChild(cubeProx);
	}
	
	public void setWheels(final double leftPercent, final double rightPercent) {
		left.set(ControlMode.PercentOutput, leftPercent);
		right.set(ControlMode.PercentOutput, rightPercent);
	}
	
	public void open() {
		fingers.set(Value.kForward);
	}
	
	public void close() {
		fingers.set(Value.kReverse);
	}
	
	public void wristUp() {
		wrist.set(Value.kForward);
	}
	
	public void wristDown() {
		wrist.set(Value.kReverse);
	}
	
	public boolean isCubeNear() {
		return !cubeProx.get();
	}
	
	public boolean getLeftLimitSwitch() {
		return leftLimitSwitch.get();
	}
	
	public boolean getRightLimitSwitch() {
		return rightLimitSwitch.get();
	}
	
	public void periodic() {
		SmartDashboard.putBoolean("has cube far", isCubeNear());	
		SmartDashboard.putBoolean("getLeftLimitSwitch", getLeftLimitSwitch());
		SmartDashboard.putBoolean("getRightLimitSwitch", getRightLimitSwitch());
	}
	
    public void initDefaultCommand() {
        
    }
}


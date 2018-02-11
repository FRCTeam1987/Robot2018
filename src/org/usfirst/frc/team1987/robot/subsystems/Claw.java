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
	
	private final WPI_TalonSRX master;
	private final WPI_TalonSRX slave;
	private final DoubleSolenoid fingers;
	private final DoubleSolenoid wrist;
	private final DigitalInput nearCubeSensor;
	private final DigitalInput hasCubeSensor;
	
	public Claw() {
		master = new WPI_TalonSRX(RobotMap.clawMaster);
		slave = new WPI_TalonSRX(RobotMap.clawSlave);
		fingers = new DoubleSolenoid(RobotMap.pcmOther, RobotMap.clawFingersOpen, RobotMap.clawFingersClosed);
		wrist = new DoubleSolenoid(RobotMap.pcmOther, RobotMap.clawWristUp, RobotMap.clawWristDown);
		nearCubeSensor = new DigitalInput(RobotMap.clawNearCubeSensor);
		hasCubeSensor = new DigitalInput(RobotMap.clawHasCubeSensor);
		
		slave.follow(master);
		
		addChild(master);
		addChild(slave);
		addChild(fingers);
		addChild(nearCubeSensor);
		addChild(hasCubeSensor);
	}
	
	public void setWheels(final double percent) {
		master.set(ControlMode.PercentOutput, percent);
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
	
	public boolean hasCube() {
		return !hasCubeSensor.get();
	}
	
	public boolean isCubeNear() {
		return !nearCubeSensor.get();
	}
	
	public void periodic() {
		SmartDashboard.putBoolean("has cube far", hasCube());
		SmartDashboard.putBoolean("has cube close", isCubeNear());
		
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


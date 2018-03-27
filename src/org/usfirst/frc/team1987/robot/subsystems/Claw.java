package org.usfirst.frc.team1987.robot.subsystems;

import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.util.DigitalDebouncer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
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
	private DigitalDebouncer leftLimitSwitchDebouncer;	
	private DigitalDebouncer rightLimitSwitchDebouncer;
	private boolean isStrongEject;
	
	public Claw() {
		right = new WPI_TalonSRX(RobotMap.clawRight);
		left = new WPI_TalonSRX(RobotMap.clawLeft);
		fingers = new DoubleSolenoid(RobotMap.pcmOther, RobotMap.clawFingersOpen, RobotMap.clawFingersClosed);
		wrist = new DoubleSolenoid(RobotMap.pcmOther, RobotMap.clawWristUp, RobotMap.clawWristDown);
		cubeProx = new DigitalInput(RobotMap.clawCubeProx);
		leftLimitSwitch = new DigitalInput(RobotMap.clawLeftLimitSwitch);
		rightLimitSwitch = new DigitalInput(RobotMap.clawRightLimitSwitch);
		leftLimitSwitchDebouncer = new DigitalDebouncer(RobotMap.debounceTime);
		rightLimitSwitchDebouncer = new DigitalDebouncer(RobotMap.debounceTime);
		
		wristUp();
				
		addChild(right);
		addChild(left);
		addChild(fingers);
		addChild(cubeProx);
	}
	
	public void setWheels(final double leftPercent, final double rightPercent) {
		left.set(ControlMode.PercentOutput, leftPercent);
		right.set(ControlMode.PercentOutput, rightPercent);
	}
	
	public void setRumble(final double power) {
		Robot.oi.getDriver().setRumble(RumbleType.kLeftRumble, power);
    	Robot.oi.getDriver().setRumble(RumbleType.kRightRumble,power);
	}
	
	public void setEjectSpeed(boolean isStrongEject) {
		this.isStrongEject = isStrongEject;
	}
	
	public boolean isStrongEject() {
		return isStrongEject;
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
	
	public boolean isWristUp() {
		return wrist.get() == Value.kForward;
	}
	
	public void toggleWrist() {
		if (isWristUp()) {
			wrist.set(Value.kReverse);
			SmartDashboard.putString("Wrist Status", "Down");
		} 
		else {
			wrist.set(Value.kForward);
			SmartDashboard.putString("Wrist Status", "Up");
		}
	}
	
	public void toggleEjectSpeed() {
		if (isStrongEject()) {
			setEjectSpeed(false);
		}
		else {
			setEjectSpeed(true);
		}
	}
	
	public boolean isCubeNear() {
		return !cubeProx.get();
	}
	
	public boolean getLeftLimitSwitch() {
		return !leftLimitSwitch.get();
	}
		
	public boolean getRightLimitSwitch() {
		return !rightLimitSwitch.get();
	}
	
	public boolean isLeftLimitSwitchTriggered() {		
		return leftLimitSwitchDebouncer.get();
	}
	
	public boolean isRightLimitSwitchTriggered() {		
		return rightLimitSwitchDebouncer.get();
	}
	
	public void periodic() {
		SmartDashboard.putBoolean("Claw - near", isCubeNear());
		leftLimitSwitchDebouncer.periodic(getLeftLimitSwitch());
		rightLimitSwitchDebouncer.periodic(getRightLimitSwitch());
		SmartDashboard.putBoolean("Claw - left", isLeftLimitSwitchTriggered());
		SmartDashboard.putBoolean("Claw - right", isRightLimitSwitchTriggered());
		SmartDashboard.putBoolean("Is Strong Eject", isStrongEject());
	}
	
    public void initDefaultCommand() {
        
    }
}


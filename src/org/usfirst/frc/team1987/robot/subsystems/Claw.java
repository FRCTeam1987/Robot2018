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

	private final WPI_TalonSRX clawMaster;
	private final WPI_TalonSRX clawSlave;
	private final DoubleSolenoid clawActuator;
	private final DigitalInput farCubeSensor;
	private final DigitalInput closeCubeSensor;
	
	public Claw() {
		
		clawMaster = new WPI_TalonSRX(RobotMap.clawMasterID);
		clawSlave = new WPI_TalonSRX(RobotMap.clawSlaveID);
		clawActuator = new DoubleSolenoid(RobotMap.clawExtendPCMID, RobotMap.clawRetractPCMID);
		farCubeSensor = new DigitalInput(RobotMap.farCubeSensorDIOID);
		closeCubeSensor = new DigitalInput(RobotMap.closeCubeSensorDIOID);
		
		clawSlave.follow(clawMaster);
		clawMaster.configPeakOutputForward(1, 0);
    	clawMaster.configPeakOutputReverse(-1, 0);
    	clawMaster.configNominalOutputForward(0, 0);
    	clawMaster.configNominalOutputReverse(0, 0);
	}
	
	public void setWheels(double voltagePercent) {
		
		clawMaster.set(ControlMode.PercentOutput, voltagePercent);
	}
	
	public void setActuator(boolean position) {
		
		clawActuator.set(position ? Value.kReverse : Value.kForward);
	}
	
	public boolean getHasCubeFar() {
		
		return !farCubeSensor.get();
	}
	
	public boolean getHasCubeClose() {
		
		return !closeCubeSensor.get();
	}
	
	public void periodic() {
		SmartDashboard.putBoolean("has cube far", getHasCubeFar());
		SmartDashboard.putBoolean("has cube close", getHasCubeClose());
	}
	
    public void initDefaultCommand() {
        
    }
}


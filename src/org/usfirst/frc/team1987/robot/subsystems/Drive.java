package org.usfirst.frc.team1987.robot.subsystems;

import org.usfirst.frc.team1987.robot.DriveMode;
import org.usfirst.frc.team1987.robot.Robot;
import org.usfirst.frc.team1987.robot.RobotMap;
import org.usfirst.frc.team1987.robot.commands.drive.TeleopDrive;
import org.usfirst.frc.team1987.robot.commands.drive.ToggleShifter;
import org.usfirst.frc.team1987.util.Util;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
//
//enum DriveMode {
//	PIVOT,
//	STRAIGHT,
//	TRAJECTORY
//}

/**
 *
 */
public class Drive extends Subsystem {

	private final WPI_TalonSRX leftMaster;
	private final WPI_TalonSRX leftSlave1;
	private final WPI_TalonSRX leftSlave2;
	private final WPI_TalonSRX rightMaster;
	private final WPI_TalonSRX rightSlave1;
	private final WPI_TalonSRX rightSlave2;
	private final DifferentialDrive robotDrive;
	private final DoubleSolenoid shifter;
	private final DoubleSolenoid pto;
	private final Solenoid dropDownOmniBack;
	private final Solenoid dropDownOmniFront;
	private final AHRS ahrs;
	private boolean isBrake;
	
	public Drive() {
		ahrs = new AHRS(SPI.Port.kMXP);
		leftMaster = new WPI_TalonSRX(RobotMap.leftMasterID);
		leftSlave1 = new WPI_TalonSRX(RobotMap.leftSlave1ID);
		leftSlave2 = new WPI_TalonSRX(RobotMap.leftSlave2ID);
		rightMaster = new WPI_TalonSRX(RobotMap.rightMasterID);
		rightSlave1 = new WPI_TalonSRX(RobotMap.rightSlave1ID);
		rightSlave2 = new WPI_TalonSRX(RobotMap.rightSlave2ID);
		robotDrive = new DifferentialDrive(leftMaster, rightMaster);
		shifter = new DoubleSolenoid(RobotMap.pcmDrive, RobotMap.shifterHigh, RobotMap.shifterLow);
		pto = new DoubleSolenoid(RobotMap.pcmDrive, RobotMap.drivePTOEngaged, RobotMap.drivePTODisengaged);
		dropDownOmniBack = new Solenoid(RobotMap.pcmDrive, RobotMap.driveDropDownOmniBack);
		dropDownOmniFront = new Solenoid(RobotMap.pcmDrive, RobotMap.driveDropDownOmniFront);
		
		leftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, RobotMap.defaultTimeout);
		final ErrorCode leftEncoderErrorCode = leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.drivePIDIDX, RobotMap.defaultTimeout);
		if (leftEncoderErrorCode != ErrorCode.OK) {
			SmartDashboard.putString("Left Drive Encoder Status", leftEncoderErrorCode.toString());
		} 
		else if (leftEncoderErrorCode == ErrorCode.OK) {
			SmartDashboard.putString("Left Drive Encoder Status", leftEncoderErrorCode.toString());
		}
		
		leftMaster.configPeakOutputForward(1, 0);
		leftMaster.configPeakOutputReverse(-1, 0);
		leftMaster.configNominalOutputForward(0.0, 0);
		leftMaster.configNominalOutputReverse(0.0, 0);
		leftMaster.setNeutralMode(NeutralMode.Brake);
		leftMaster.configOpenloopRamp(0.15, RobotMap.defaultTimeout);
		
		rightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, RobotMap.defaultTimeout);
		final ErrorCode rightEncoderErrorCode = rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, RobotMap.drivePIDIDX, RobotMap.defaultTimeout);
		if (rightEncoderErrorCode != ErrorCode.OK) {
			SmartDashboard.putString("Right Drive Encoder Status", rightEncoderErrorCode.toString());
		}
		else if (rightEncoderErrorCode == ErrorCode.OK) {
			SmartDashboard.putString("Right Drive Encoder Status", rightEncoderErrorCode.toString());
		}
		
		rightMaster.setSensorPhase(true);
		rightMaster.configPeakOutputForward(1, 0);
		rightMaster.configPeakOutputReverse(-1, 0);
		rightMaster.configNominalOutputForward(0.0, 0);
		rightMaster.configNominalOutputReverse(0.0, 0);
		rightMaster.setNeutralMode(NeutralMode.Brake);
		rightMaster.configOpenloopRamp(0.15, RobotMap.defaultTimeout);

		leftSlave1.follow(leftMaster);
		leftSlave2.follow(leftMaster);
		rightSlave1.follow(rightMaster);
		rightSlave2.follow(rightMaster);
		
		robotDrive.setSafetyEnabled(false);
		ahrsReset();
		zeroDriveEncoders();
		setCoast();
	}

	public void xboxDrive(XboxController xbox) {
		double move = xbox.getTriggerAxis(Hand.kRight) - xbox.getTriggerAxis(Hand.kLeft);
		double rotate = xbox.getX(Hand.kLeft);
		
		if(Robot.elevator.getDistance() > 10) {
			move *= .75;
			rotate *= .75;
		}
		robotDrive.arcadeDrive(move, rotate, true); //-move
	}
	
	public void tankDrive(final double left, final double right) {
		robotDrive.tankDrive(left, right);
	}
	
	public void set(final ControlMode controlMode, final double left, final double right) {
		leftMaster.set(controlMode, left);
		rightMaster.set(controlMode, right);
	}
	
	public void driveDistance(final double leftInches, final double rightInches) {
		double leftRotations = Util.distanceToRotations(leftInches,  RobotMap.wheelDiameter);
		double rightRotations = Util.distanceToRotations(rightInches, RobotMap.wheelDiameter);
		
		leftMaster.set(ControlMode.Position, leftRotations);
		rightMaster.set(ControlMode.Position, rightRotations);
	}
	
	public void zeroDriveEncoders() {
		leftMaster.setSelectedSensorPosition(0, RobotMap.drivePIDIDX, RobotMap.defaultTimeout);
		rightMaster.setSelectedSensorPosition(0, RobotMap.drivePIDIDX, RobotMap.defaultTimeout);
	}
	
	public void setHighGear() {
		shifter.set(Value.kForward);
	}
	
	public void setLowGear() {
		shifter.set(Value.kReverse);
	}
	
	private boolean isHighGear() {
		return shifter.get() == Value.kForward;
	}
	
	public void toggleShift() {		
		if(isHighGear() == true) {
			setLowGear();
			SmartDashboard.putString("Shifter status", "low gear");
		}
		else {
			dropDownOmniFrontRaise();
			dropDownOmniBackRaise();
			setHighGear();
			SmartDashboard.putString("Shifter status", "high gear");
		}		
	}
	
	private double getRightPercentOutput() {
		return Robot.drive.rightMaster.getMotorOutputPercent();
	}
	
	private double getLeftPercentOutput() {
		return Robot.drive.leftMaster.getMotorOutputPercent();
	}
	
	public int getLeftRawEncoderPosition() {
		return leftMaster.getSelectedSensorPosition(0);
	}
	
	
	public int getRightRawEncoderPosition() {
		return rightMaster.getSelectedSensorPosition(0);
	}
	
	public double getLeftEncoderDistance() {
		return Util.rotationsToDistance(Util.getCtreEncoderRotations(getLeftRawEncoderPosition()), RobotMap.wheelDiameter);
	}
	
	public double getRightEncoderDistance() {
		return Util.rotationsToDistance(Util.getCtreEncoderRotations(getRightRawEncoderPosition()), RobotMap.wheelDiameter);
	}
	
	public double getHeading() {
		return 360.0 - ahrs.getFusedHeading();
	}
	
	public double getAngle() {
		return ahrs.getAngle();
	}
	
	public double getGyroRate() {
		return ahrs.getRate();
	}
	
	public void setLeftMasterForDistance(final double leftInches) {
		final double leftTicks = Util.distanceToTicks(leftInches, RobotMap.wheelDiameter);
		leftMaster.set(ControlMode.Position, leftTicks);		
	}
	
	public void setRightMasterForDistance(final double rightInches) {
		final double rightTicks = Util.distanceToRotations(rightInches, RobotMap.wheelDiameter);
		rightMaster.set(ControlMode.Position, rightTicks);		
	}
	
	public void setCoast() {
		leftMaster.setNeutralMode(NeutralMode.Coast);
		rightMaster.setNeutralMode(NeutralMode.Coast);
		isBrake = false;
	}
	
	public void setBrake() {
		leftMaster.setNeutralMode(NeutralMode.Brake);
		rightMaster.setNeutralMode(NeutralMode.Brake);
		isBrake = true;
	}
	
	public boolean isBrake() {
		return isBrake; 
	}
	
    public EncoderFollower[] pathSetup(Trajectory toFollow) {
        EncoderFollower left = new EncoderFollower();
        EncoderFollower right = new EncoderFollower();
        TankModifier modifier = new TankModifier(toFollow).modify((Drive.DrivetrainProfiling.wheel_base_width));
        DrivetrainProfiling.last_gyro_error = 0.0;
        left = new EncoderFollower(modifier.getLeftTrajectory());
        right = new EncoderFollower(modifier.getRightTrajectory());
        left.configureEncoder(leftMaster.getSelectedSensorPosition(0), DrivetrainProfiling.ticks_per_rev, DrivetrainProfiling.wheel_diameter);
        right.configureEncoder(rightMaster.getSelectedSensorPosition(0), DrivetrainProfiling.ticks_per_rev, DrivetrainProfiling.wheel_diameter);
        left.configurePIDVA(DrivetrainProfiling.kp, DrivetrainProfiling.ki, DrivetrainProfiling.kd, DrivetrainProfiling.kv, DrivetrainProfiling.ka);
        right.configurePIDVA(DrivetrainProfiling.kp, DrivetrainProfiling.ki, DrivetrainProfiling.kd, DrivetrainProfiling.kv, DrivetrainProfiling.ka);
        return new EncoderFollower[] {
                left, // 0
                right, // 1
        };
    }
	
    public void pathFollow(EncoderFollower left, EncoderFollower right, boolean reverse) {
        double l;
        double r;
        if (!reverse) {
            l = left.calculate(-leftMaster.getSelectedSensorPosition(0));
            r = right.calculate(-rightMaster.getSelectedSensorPosition(0));
        } else {
            l = left.calculate(leftMaster.getSelectedSensorPosition(0));
            r = right.calculate(rightMaster.getSelectedSensorPosition(0));
        }
        double gyro_heading = ahrs.getAngle();
        double angle_setpoint = Pathfinder.r2d(left.getHeading());
        double angleDifference = Pathfinder.boundHalfDegrees(angle_setpoint - gyro_heading);

        double turn = DrivetrainProfiling.gp * angleDifference + (DrivetrainProfiling.gd *
                ((angleDifference - DrivetrainProfiling.last_gyro_error) / RobotMap.period));

        DrivetrainProfiling.last_gyro_error = angleDifference;
        
        l = Math.copySign(Math.min(Math.abs(l * 12.0 / Robot.pdp.getVoltage()), 1.0), l); 
        r = Math.copySign(Math.min(Math.abs(r * 12.0 / Robot.pdp.getVoltage()), 1.0), r);

        SmartDashboard.putNumber("l before set", l);
        SmartDashboard.putNumber("r before set", r);        
        
        if(!reverse) {
        	Robot.drive.tankDrive(l + turn, r - turn);
        }
        else {
        	Robot.drive.tankDrive(-l + turn, -r - turn);
        }
    }

    public void setPID(DriveMode mode) {
		double m_P;
		double m_I;
		double m_D;
		switch(mode) {
			case PIVOT: 
				if (isHighGear()) {
					m_P = RobotMap.drivePivotHighP;
					m_I = RobotMap.drivePivotHighI;
					m_D = RobotMap.drivePivotHighD;
				} else {
					m_P = RobotMap.drivePivotLowP;
					m_I = RobotMap.drivePivotLowI;
					m_D = RobotMap.drivePivotLowD;
				}
				break;
			case STRAIGHT:
				if (isHighGear()) {
					m_P = RobotMap.driveStraightHighP;
					m_I = RobotMap.driveStraightHighI;
					m_D = RobotMap.driveStraightHighD;
				} else {
					m_P = RobotMap.driveStraightLowP;
					m_I = RobotMap.driveStraightLowI;
					m_D = RobotMap.driveStraightLowD;
				}
				break; 
			default:
				return;	
		}
		
		leftMaster.config_kP(RobotMap.drivePIDIDX, m_P, RobotMap.defaultTimeout);
		leftMaster.config_kI(RobotMap.drivePIDIDX, m_I, RobotMap.defaultTimeout);
		leftMaster.config_kD(RobotMap.drivePIDIDX, m_D, RobotMap.defaultTimeout);
		
		rightMaster.config_kP(RobotMap.drivePIDIDX, m_P, RobotMap.defaultTimeout);
		rightMaster.config_kI(RobotMap.drivePIDIDX, m_I, RobotMap.defaultTimeout);
		rightMaster.config_kD(RobotMap.drivePIDIDX, m_D, RobotMap.defaultTimeout);
	}
	
    public void ahrsReset() {
    	ahrs.reset();
    }
    
	public void zeroHeading() {
		ahrs.zeroYaw(); 	//might need to be changed
	}
	
	public void ptoDisengage() {
		pto.set(Value.kReverse);
	}
	
	public void ptoEngage() {
		pto.set(Value.kForward);
	}
	
	public boolean isPtoEngaged() {
		return pto.get() == Value.kForward;
	}
	
	public void ptoToggle() {
		if(isPtoEngaged()) {
			ptoDisengage();
		} else {
			ptoEngage();
		}
	}
	
	public void dropDownOmniBackRaise() {
		dropDownOmniBack.set(false);
	}
	
	public void dropDownOmniBackLower() {
		dropDownOmniBack.set(true);
	}
	
	public void dropDownOmniBackToggle() {
		dropDownOmniBack.set(!dropDownOmniBack.get());
	}
	
	public void dropDownOmniFrontRaise() {
		dropDownOmniFront.set(false);
	}
	
	public void dropDownOmniFrontLower() {
		dropDownOmniFront.set(true);
	}
	
	public void dropDownOmniFrontToggle() {
		dropDownOmniFront.set(!dropDownOmniFront.get());
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new TeleopDrive());
    }
    
    public void periodic() {
    	SmartDashboard.putNumber("left inches", getLeftEncoderDistance());
    	SmartDashboard.putNumber("right inches", getRightEncoderDistance());  	
    	SmartDashboard.putNumber("heading", getHeading());
    	SmartDashboard.putNumber("left-percent", leftMaster.get());
    	SmartDashboard.putNumber("right-percent", rightMaster.get());
    }
    public static class DrivetrainProfiling {
        //TODO: TUNE CONSTANTS
        public static double kp = 0.8;	//low gear autos are 1.2	//like 0.8 for straighish	//like 0.9 for lot-o-turns
        public static double kd = 0.0; //low gear autos are	// 0.35	//like 0.0 for straighish and low gear	//like 0.4 for lot-o-turns
        public static double ki = 0.0;
        
        // These are used in calculating turning
        public static double dt = 0.026;  
        public static double gp = 0.037;	//low gear is .04	// like 0.037 for both straighishers and lot-o-turns
        // Increasing gd more aggressively pursues the target heading
        public static double gd = 0.0;	//low gear is 0.0	// 0.0025	//0.025	//like 0.0 for straightisher paths and lot-o-turns

        //gyro logging
        public static double last_gyro_error = 0.0;

        public static final double max_velocity = 3.4;	//low gear is 2.0	// like 3.4 for straight paths	and lot-o-turns
        public static final double kv = 1.0 / max_velocity; // Calculated for test Drivetrain
        public static final double max_acceleration = 1.4;	//low gear is 2.0	// like 1.4 for straighterish paths	//like 1.125 or 1.2(double check) for lot-o-turns
        public static final double ka = 0.0; //0.015
        public static final double max_jerk = 7.62;
        public static final double wheel_diameter = 0.117475; //0.117475
        public static final double wheel_base_width = 0.61595;
        public static final int ticks_per_rev = 4096; // CTRE Mag Encoder

        public static void setPIDG(double p, double i, double d, double gp, double gd) {
            SmartDashboard.putNumber("kP", p);
            SmartDashboard.putNumber("kI", i);
            SmartDashboard.putNumber("kD", d);
            SmartDashboard.putNumber("gP", gp);
            SmartDashboard.putNumber("gD", gd);
        }

        public static void updatePIDG() {
            kp = SmartDashboard.getNumber("kP", 0.0);
            ki = SmartDashboard.getNumber("kI", 0.0);
            kd = SmartDashboard.getNumber("kD", 0.0);
            gp = SmartDashboard.getNumber("gP", 0.0);
            gd = SmartDashboard.getNumber("gD", 0.0);
        }
    }
}


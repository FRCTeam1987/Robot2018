package org.usfirst.frc.team1987.robot.commands.drive;

import org.usfirst.frc.team1987.robot.Robot;
import java.lang.Math;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightForDistance extends Command {

//	private final double deltaDistance;
//	private double m_initialSpeed;
//  private double m_finalSpeed;
	private final double m_targetDistance;
    private double m_targetHeading;
    private double m_currentHeading;
	
    public DriveStraightForDistance(final double targetDistance) {
         requires(Robot.drive);
         setTimeout(2);
//       m_initialSpeed = initialSpeed;
//       m_finalSpeed = finalSpeed;
         m_targetDistance = targetDistance;
         m_targetHeading = 0;
         
    }


    protected void initialize() {
    	Robot.drive.zeroDriveEncoders();
    	Robot.drive.zeroYaw();
    	
    }
 

    protected void execute() {
    	if (m_targetDistance > 0)
    		Robot.drive.tankDrive(.9, .9);
    	else if(m_targetDistance < 0)
    		Robot.drive.tankDrive(-.9, -.9); 
    	
    	m_currentHeading = Robot.drive.getYaw();
    	
    	if (m_currentHeading - m_targetHeading > 1)
    		Robot.drive.tankDrive(.75, .9);
    	else if (m_currentHeading - m_targetHeading < -1)
    		Robot.drive.tankDrive(.9, .75);
    	    	
    }


    protected boolean isFinished() {
    	return Math.abs(m_targetDistance) - Math.abs(Robot.drive.getLeftEncoderDistance()) < 1 
    			&& Math.abs(m_targetDistance) - Math.abs(Robot.drive.getRightEncoderDistance()) < 1; 
    	
    }

    protected void end() {
    	Robot.drive.tankDrive(0, 0);
    }


    protected void interrupted() {
    	Robot.drive.tankDrive(0, 0);
    }
}

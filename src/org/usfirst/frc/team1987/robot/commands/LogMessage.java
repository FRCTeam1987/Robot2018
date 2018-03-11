package org.usfirst.frc.team1987.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class LogMessage extends InstantCommand {

	private final String message;
	
    public LogMessage(final String message) {
        super();
        this.message = message;
    }

    // Called once when the command executes
    protected void initialize() {
    	final double currentTime = Timer.getFPGATimestamp();
    	System.out.print(currentTime);
    	System.out.println(message);
    }

}

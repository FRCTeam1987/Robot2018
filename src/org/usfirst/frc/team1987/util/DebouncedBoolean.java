package org.usfirst.frc.team1987.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DebouncedBoolean {

	private int counter = 0;
	private int minLoops;
	
	public DebouncedBoolean(double seconds) {
		minLoops = (int)Math.ceil(seconds / 0.02);
	}
	
	public boolean get() {
		return counter > minLoops;
	}
	
	public void update(boolean value) {
		if(value) {
			counter++;
		}
		else {
			counter = 0;
		}
		SmartDashboard.putNumber("Loop counter", counter);
	}
	
}

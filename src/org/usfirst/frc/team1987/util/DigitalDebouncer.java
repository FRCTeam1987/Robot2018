package org.usfirst.frc.team1987.util;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DigitalDebouncer {

	private boolean m_wasPreviouslyPressed;
	private double m_timeStamp;
	private final double m_duration;
	
	public DigitalDebouncer(final double duration) {
		m_duration = duration;
		m_timeStamp = Timer.getFPGATimestamp() + 100;
	}
	
	public boolean get() {
		return Timer.getFPGATimestamp() - m_timeStamp > m_duration;
	}
	
	public void periodic(boolean isCurrentlyPressed) {
		if(!m_wasPreviouslyPressed && isCurrentlyPressed) {
			m_timeStamp = Timer.getFPGATimestamp();
			m_wasPreviouslyPressed = true;
		} else if(!isCurrentlyPressed) {
			m_wasPreviouslyPressed = false;
			m_timeStamp = Timer.getFPGATimestamp();
		}
	}
}

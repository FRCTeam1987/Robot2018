package org.usfirst.frc.team1987.util;

import org.usfirst.frc.team1987.robot.RobotMap;

public class Util {
	private static double circumference(final double diameter) {
		return Math.PI * diameter;
	}
	
	public static double getCtreEncoderRotations(final double encoderTicks) {
		return encoderTicks / RobotMap.ticksPerRotation;
	}
	
	public static double rotationsToDistance(final double rotations, final double diameter) {
		return circumference(diameter) * rotations;
	}
	
	public static double distanceToRotations(final double distance, final double diameter) {
		return distance / circumference(diameter);
	}
	
	public static int rotationsToTicks(final double rotations) {
		return (int)(rotations * RobotMap.ticksPerRotation);
	}
	
	public static int distanceToTicks(final double distance, final double diameter) {
		return (int)rotationsToTicks(distanceToRotations(distance, diameter));
	}
	
	public static double ticksToDistance(final int ticks, final double diameter) {
//		return circumference(diameter) * getCtreEncoderRotations(ticks);
		return rotationsToDistance(getCtreEncoderRotations(ticks), diameter);
//		Util.rotationsToDistance(Util.getCtreEncoderRotations(getTicks()), RobotMap.winchDiameter
	}
}

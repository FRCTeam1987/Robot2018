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
	
	public static double rotationsToTicks(final double rotations) {
		return (int)rotations * RobotMap.ticksPerRotation;
	}
	
	public static int distanceToTicks(final double distance, final double diameter) {
		return (int)rotationsToTicks(distanceToRotations(distance, diameter));
	}
}

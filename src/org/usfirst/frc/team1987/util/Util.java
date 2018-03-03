package org.usfirst.frc.team1987.util;

import org.usfirst.frc.team1987.robot.RobotMap;

public class Util {
	public static double circumference(final double diameter) {
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
	
	public static double limit(final double value, final double minimum, final double maximum){
		if(value < minimum) {
			return minimum;
		}
		if(value > maximum) {
			return maximum;
		}
		return value;
	}
	
	public static double limit(final double value){
		return limit(value, -1.0, 1.0);	
	}
	
	public static boolean isWithinTolerance(final double value, final double target, final double tolerance){
		return value > target - Math.abs(tolerance) && value < target + Math.abs(tolerance);
	}
}
package org.usfirst.frc.team1987.util;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

public class AutoPaths {

//    private static final double drivetrainLength = 0.79375;

    public static Waypoint[] toSwitch = new Waypoint[] {
    		new Waypoint(0.0,0.0, Pathfinder.d2r(0.0)),
    		new Waypoint(2.743, 0.0, Pathfinder.d2r(0.0)),
    };

    public static Waypoint[] postBackup = new Waypoint[] {
			new Waypoint(0.0,0.0, Pathfinder.d2r(0.0)),
			new Waypoint(1.0,1.25, Pathfinder.d2r(0.0)),
			new Waypoint(4.8,1.5, Pathfinder.d2r(0.0)),
    };

    public static Waypoint[] backUp = new Waypoint[] {
			new Waypoint(0.0,0.0, Pathfinder.d2r(0.0)),
			new Waypoint(1.0, 0.0, Pathfinder.d2r(0.0)),
    };

    public static Waypoint[] oneMeter = new Waypoint[] {
			new Waypoint(0.0,0.0, Pathfinder.d2r(0.0)),
			new Waypoint(1.0, 0.0, Pathfinder.d2r(0.0)),
    };

    public static Waypoint[] sPath = new Waypoint[] {
			new Waypoint(0.0,0.0, Pathfinder.d2r(0.0)),
			new Waypoint(1.0, 1.0, Pathfinder.d2r(0.0)),
    };
    
    public static Waypoint[] leftSwitchSwoop = new Waypoint[] {
    		new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
    		new Waypoint(1.25, .8, Pathfinder.d2r(90)),
    		new Waypoint(0.2, 2, Pathfinder.d2r(180)), //1.9
    		new Waypoint(-1.8, 2, Pathfinder.d2r(180)),
    		new Waypoint(-3.1, 1.5, Pathfinder.d2r(180)), //-2.7, 1.75
    };
    
    public static Waypoint[] rightSwitchSwoop = new Waypoint[] {
    		new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
    		new Waypoint(1.25, -0.8, Pathfinder.d2r(-90)),
    		new Waypoint(0.2, -2.0, Pathfinder.d2r(-180)), 
    		new Waypoint(-1.7, -2.0, Pathfinder.d2r(-180)),	//-1.8
    		new Waypoint(-3.0, -1.5, Pathfinder.d2r(-180)), //-3.1
    };
    
    public static Waypoint[] rightSwitchToLeftSwitchCube = new Waypoint[] {
    		new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
    		new Waypoint(1.4, 1.6, Pathfinder.d2r(90.0)),
    		new Waypoint(1.4, 1.85, Pathfinder.d2r(90)),		//1.8
    		new Waypoint(0.0, 3.55, Pathfinder.d2r(180.0)),	//3.6
			new Waypoint(-3.2, 3.55, Pathfinder.d2r(180.0))	//3.6
    };

    public static Waypoint[] rightSwitchToRightScale = new Waypoint[] {
    		new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
    		new Waypoint(0.5, 0.0, Pathfinder.d2r(0.0)),
    		new Waypoint(2.65, -1.55, Pathfinder.d2r(-70)),	
    };
    
    public static Waypoint[] toScale = new Waypoint[] {
			new Waypoint(0.0,0.0, Pathfinder.d2r(0.0)),
			new Waypoint(3.0, 0.0, Pathfinder.d2r(0.0)),
			new Waypoint(6.451, 1.0, Pathfinder.d2r(0.0)), //x:21.1 y:3.2
    };
    
    public static Waypoint[] toScaleSwoop = new Waypoint[] { // this path is tuned and works properly
			new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
			new Waypoint(4.0, 0.5, Pathfinder.d2r(20)),
			new Waypoint(6.4, 1.4, Pathfinder.d2r(0))
    };

    public static Waypoint[] leftToLeftScale = new Waypoint[] {		//was toNearScale
			new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),	
			new Waypoint(5.25, 0.0, Pathfinder.d2r(0.0)),
			new Waypoint(6.4, 0.5, Pathfinder.d2r(70))
    };
    
    public static Waypoint[] leftToLeftSwitch = new Waypoint[] {
			new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
			new Waypoint(2.0, 0.0, Pathfinder.d2r(0.0)),
			new Waypoint(3.0, 1.1, Pathfinder.d2r(90.0)),
			new Waypoint(3.0, 1.25, Pathfinder.d2r(90.0))
    };
    
    public static Waypoint[] leftScaleToRightSwitch = new Waypoint[] {
			new Waypoint(0.0 ,0.0,Pathfinder.d2r(0.0)),
			new Waypoint(0.95, 0.0,Pathfinder.d2r(0.0)),
			new Waypoint(1.15 ,-0.3,Pathfinder.d2r(-60.0)),
			new Waypoint(3.0 ,-3.2,Pathfinder.d2r(-60.0)),
    };
    
    public static Waypoint[] leftToRightScale = new Waypoint[] { // this path is tuned and works properly		//was toFarScale
			new Waypoint(0.0,0.0, Pathfinder.d2r(0.0)),
			new Waypoint(2.9, 0.0, Pathfinder.d2r(0.0)),	
			new Waypoint(5.25, 3.6, Pathfinder.d2r(90.0)),	
			new Waypoint(5.2, 5.3, Pathfinder.d2r(90.0)),
			new Waypoint(5.59, 5.8, Pathfinder.d2r(-25.0)),	
    };
    
//    public static Waypoint[] leftToRightSwitch = new Waypoint[] { // TODO: make this path
//			new Waypoint(0.0,0.0, Pathfinder.d2r(0.0)),
//			new Waypoint(2.9, 0.0, Pathfinder.d2r(0.0)),	
//			new Waypoint(5.25, 3.1, Pathfinder.d2r(90.0)),	
////			new Waypoint(4.7, 3.6, Pathfinder.d2r(135.0)),
////			new Waypoint(4.8, 3.7, Pathfinder.d2r(135.0)),	
//    };
    
//    public static Waypoint[] rightToLeftSwitch = new Waypoint[] { // TODO: make this path
//			new Waypoint(0.0,0.0, Pathfinder.d2r(0.0)),
//			new Waypoint(2.9, 0.0, Pathfinder.d2r(0.0)),	
//			new Waypoint(5.25, 3.1, Pathfinder.d2r(-90.0)),	
////			new Waypoint(4.7, 3.6, Pathfinder.d2r(-135.0)),
////			new Waypoint(4.8, 3.7, Pathfinder.d2r(-135.0)),	
//    };
    
    public static Waypoint[] rightToLeftScale = new Waypoint[] { // this path is the inverse of toFarScale	//was rightStartToLeftScale
    		new Waypoint(0.0,0.0, Pathfinder.d2r(0.0)),
			new Waypoint(2.9, 0.0, Pathfinder.d2r(0.0)),	
			new Waypoint(5.3, -3.6, Pathfinder.d2r(-90.0)),			// x was 5.25
			new Waypoint(5.25, -5.20, Pathfinder.d2r(-90.0)),	
			new Waypoint(5.85, -5.675, Pathfinder.d2r(25.0)),		// x was 5.7
    };
    
    public static Waypoint[] rightToLeftScaleExtraSketch = new Waypoint[] { 
    		new Waypoint(0.0,0.0, Pathfinder.d2r(0.0)),
			new Waypoint(0.25, 0.0, Pathfinder.d2r(0.0)),	
			new Waypoint(1.25, -1.0, Pathfinder.d2r(-90.0)),			
			new Waypoint(1.25, -3.5, Pathfinder.d2r(-90.0)),	
			new Waypoint(3.5, -6.65, Pathfinder.d2r(0.0)),
			new Waypoint(4.5, -6.65, Pathfinder.d2r(0.0)),
			new Waypoint(5.5, -6.65, Pathfinder.d2r(0.0)),
			new Waypoint(6.5, -6.65, Pathfinder.d2r(0.0)),
			new Waypoint(7.05, -6.20, Pathfinder.d2r(80.0)),
    };
    
    public static Waypoint[] straightForMeterAndSome = new Waypoint[] {
    		new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
    		new Waypoint(1.35, 0.0, Pathfinder.d2r(0.0))
    };
    
    public static Waypoint[] straightForMeterAndSomeMore = new Waypoint[] {
			new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
			new Waypoint(1.45, 0.0, Pathfinder.d2r(0.0))
    };
    
    public static Waypoint[] straightForMeterAndASkosh = new Waypoint[] {
    		new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
			new Waypoint(1.357, 0.0, Pathfinder.d2r(0.0))	//was 1.27
    };
     
    public static Waypoint[] straightForSkosh = new Waypoint[] {
			new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
			new Waypoint(.5, 0.0, Pathfinder.d2r(0.0))
    };
    
    public static Waypoint[] straightForLessThanSkosh = new Waypoint[] {
			new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
			new Waypoint(.3, 0.0, Pathfinder.d2r(0.0))
    };
    
    public static Waypoint[] backupAScosh = new Waypoint[] {
			new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
			new Waypoint(-0.2, 0.0, Pathfinder.d2r(0.0))
    };
    
    public static Waypoint[] rightSwitchFromMiddle = new Waypoint[] {
			new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
			new Waypoint(3.1, 1.4, Pathfinder.d2r(0.0))	//was 2.6
    };
    
    public static Waypoint[] leftSwitchFromMiddle = new Waypoint[] {
			new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
			new Waypoint(3.1, -1.5, Pathfinder.d2r(0.0))	//was 2.6
    };
    
    public static Waypoint[] rightToLeftSwitch = new Waypoint[] {
			new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
			new Waypoint(0.25, 0.0, Pathfinder.d2r(0.0)),
			new Waypoint(1.0, -0.8, Pathfinder.d2r(-90)),
			new Waypoint(1.0, -2.5, Pathfinder.d2r(-90)),
			new Waypoint(1.9, -4.5, Pathfinder.d2r(0.0)),
			new Waypoint(2.6, -4.5, Pathfinder.d2r(0.0))	
    };
    
    public static Waypoint[] rightToRightSwitch = new Waypoint[] {
			new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
			new Waypoint(2.0, 0.0, Pathfinder.d2r(0.0)),
			new Waypoint(3.0, -1.25, Pathfinder.d2r(-70.0))
    };
    
    public static Waypoint[] rightToRightScale = new Waypoint[] {
    		new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),	
			new Waypoint(5.25, 0.0, Pathfinder.d2r(0.0)),
			new Waypoint(6.3, -0.5, Pathfinder.d2r(-60))
    };
}

package org.usfirst.frc.team1987.util;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

import java.nio.file.Path;

public class AutoPaths {

    private static final double drivetrainLength = 0.79375;

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

    public static Waypoint[] toNearScale = new Waypoint[] {
    		new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),	
    		new Waypoint(5.25, 0.0, Pathfinder.d2r(0.0)),
    		new Waypoint(6.4, 0.5, Pathfinder.d2r(60))
    };
    
    public static Waypoint[] toFarScale = new Waypoint[] { // this path is tuned and works properly
    		new Waypoint(0.0,0.0, Pathfinder.d2r(0.0)),
    		new Waypoint(2.9, 0.0, Pathfinder.d2r(0.0)),	
    		new Waypoint(5.25, 3.6, Pathfinder.d2r(90.0)),	
    		new Waypoint(5.2, 5.3, Pathfinder.d2r(90.0)),
    		new Waypoint(5.59, 5.8, Pathfinder.d2r(-25.0)),	
    };
    
    public static Waypoint[] straightForMeterAndSome = new Waypoint[] {
    		new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
    		new Waypoint(1.35, 0.0, Pathfinder.d2r(0.0))
    };
    
    public static Waypoint[] backupAScosh = new Waypoint[] {
    		new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
    		new Waypoint(-0.2, 0.0, Pathfinder.d2r(0.0))
    };
    
    public static Waypoint[] rightSwitchFromMiddle = new Waypoint[] {
    		new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
    		new Waypoint(2.734, -3.0, Pathfinder.d2r(0.0))
    };
    
    public static Waypoint[] leftSwitchFromMiddle = new Waypoint[] {
    		new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
    		new Waypoint(2.734, -3.0, Pathfinder.d2r(0.0))
    };
}

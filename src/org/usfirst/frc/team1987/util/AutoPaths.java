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
            new Waypoint(6.45, 1.0, Pathfinder.d2r(0.0)),
    };
    
    public static Waypoint[] toScaleSwoop = new Waypoint[] {
    		new Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)), 
    		new Waypoint(6.25, 0.00, Pathfinder.d2r(45))
    };

    public static Waypoint[] toFarScale = new Waypoint[] {
            new Waypoint(0.0,0.0, Pathfinder.d2r(0.0)),
            new Waypoint(4.250, 0.5, Pathfinder.d2r(45.0)),
            new Waypoint(4.500, 3.0, Pathfinder.d2r(0.0)),
//            new Waypoint(6.702, 4.57, Pathfinder.d2r(0.0)),
    };
}

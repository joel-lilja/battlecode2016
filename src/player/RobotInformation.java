package player;

import battlecode.common.*;

import java.util.Random;

/**
 * Created by joel on 11/12/2016.
 */

public class RobotInformation  {
    Direction[] directions = {Direction.NORTH, Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST,
            Direction.SOUTH, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST};
    RobotType[] robotTypes = {RobotType.SCOUT, RobotType.SOLDIER, RobotType.SOLDIER, RobotType.SOLDIER,
            RobotType.GUARD, RobotType.GUARD, RobotType.VIPER, RobotType.TURRET};
    Random rand = new Random(); //rc.getID()
    RobotController rc;

    public RobotInformation(RobotController rc){
        this.rc = rc;
    }

    public int GetRandomInt(){
        return rand.nextInt();
    }
    public Direction GetRandomDirection(){
        return directions[GetRandomInt() % 8];
    }
}
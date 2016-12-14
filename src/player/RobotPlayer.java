package player;

import battlecode.common.*;

public class RobotPlayer {

    /**
     * run() is the method that is called when a robot is instantiated in the Battlecode world.
     * If this method returns, the robot dies!
     **/
    @SuppressWarnings("unused")


    public static void run(RobotController rc) {
        // You can instantiate variables here.
        int myAttackRange = 0;
        Team myTeam = rc.getTeam();
        Team enemyTeam = myTeam.opponent();

        RobotInformation ri = new RobotInformation(rc);

        if (rc.getType() == RobotType.ARCHON) {
            try {
                // Any code here gets executed exactly once at the beginning of the game.
            } catch (Exception e) {
                // Throwing an uncaught exception makes the robot die, so we need to catch exceptions.
                // Caught exceptions will result in a bytecode penalty.
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            while (true) {
                // This is a loop to prevent the run() method from returning. Because of the Clock.yield()
                // at the end of it, the loop will iterate once per game round.
                Build(ri);
            }
        } else if (rc.getType() != RobotType.TURRET) {
            try {

                // Any code here gets executed exactly once at the beginning of the game.
              //  myAttackRange = rc.getType().attackRadiusSquared;
            } catch (Exception e) {
                // Throwing an uncaught exception makes the robot die, so we need to catch exceptions.
                // Caught exceptions will result in a bytecode penalty.
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            while (true) {
                // This is a loop to prevent the run() method from returning. Because of the Clock.yield()
                // at the end of it, the loop will iterate once per game round.
                try {
                    //do the things here
                    if(rc.isCoreReady()){
                        Move(ri);


                    }

                    Clock.yield();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        } else if (rc.getType() == RobotType.TURRET) {
            try {
                myAttackRange = rc.getType().attackRadiusSquared;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            while (true) {
                // This is a loop to prevent the run() method from returning. Because of the Clock.yield()
                // at the end of it, the loop will iterate once per game round.
                try {
                    Clock.yield();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    private static void Move(RobotInformation ri) throws GameActionException {
        RobotController rc = ri.rc;
        Direction dirToMove = ri.GetRandomDirection();
        // Check the rubble in that direction
        if (rc.senseRubble(rc.getLocation().add(dirToMove)) >= GameConstants.RUBBLE_OBSTRUCTION_THRESH) {
            // Too much rubble, don't clear
            //rc.clearRubble(dirToMove);
            // Check if I can move in this direction
        } else if (rc.canMove(dirToMove)) {
            // Move
            rc.move(dirToMove);
        }
    }

    private static void Build(RobotInformation ri) {
        try {
            if(ri.rc.isCoreReady()){
                BuildSoldier(ri);
            }
            Clock.yield();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void BuildSoldier(RobotInformation ri) throws GameActionException {
        RobotController rc = ri.rc;
        RobotType robotType = RobotType.SOLDIER;
        if(rc.hasBuildRequirements(robotType)){
            Direction buildDirection = GetBuildDirection(ri);
            if(buildDirection != null){
                rc.build(buildDirection, robotType);
            }
        }
    }

    private static Direction GetBuildDirection(RobotInformation ri)
    {
        Direction direction = ri.GetRandomDirection();

        for(int i = 0; i < ri.directions.length; i++)
        {
            if(!ri.rc.canMove(direction)) {
                direction.rotateLeft();
                if(i == ri.directions.length){
                    direction = null;
                }
            }
            else{
                break;
            }
        }

        return direction;
    }
}

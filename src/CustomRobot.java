import becker.robots.*;

import becker.robots.Robot;

import java.awt.*;

import static dit948.Random.*;

/**
 * A custom robot class mainly used for the player robot
 */
public class CustomRobot extends RobotSE implements Runnable{
    protected boolean isPlayer;
    protected boolean breakBot = false;
    protected boolean crashedBot = false;
    CustomRobot enemyRobot;
    Frame frame;
    RobotUIComponents components;


    public CustomRobot (City city, int xStreet, int yStreet, Direction direction, CustomRobot enemyRobot) {
        super(city, xStreet, yStreet, direction);
        this.enemyRobot = enemyRobot;
    }

    public CustomRobot(City city, int xStreet, int yStreet, Direction direction) {
        super(city, xStreet, yStreet, direction);
    }

    public CustomRobot(City city, int xStreet, int yStreet, Direction direction, boolean isPlayer) {
        super(city, xStreet, yStreet, direction);
        this.isPlayer = isPlayer;
    }

    public void randomMove() {
        int nrTurns = randomInt(4);
        double speed = getSpeed();
        if (nrTurns > 0)
            setSpeed(nrTurns * speed);
        for (int i = 0; i < nrTurns; i++)
            turnLeft();
        setSpeed(speed);
        move();

    }

    public boolean intersects(){
        if(this.getIntersection()==enemyRobot.getIntersection())
            return true;

        return false;
    }

    public void go(int steps) {
        for (int i = 0; i != steps; i++) { // a potentially infinite loop
            randomMove();
        }
    }

    public void turnRight() {
        double speed = getSpeed();
        setSpeed(3 * speed);
        turnLeft();
        turnLeft();
        turnLeft();
        setSpeed(speed);
    }

    public void pickThing() {
        if (canPickThing())
            super.pickThing();
    }

    public void moveR() {
        if (frontIsClear())
            super.move();
        if (this.intersects()){
            Main.winLoseWindow(false, frame, this, components);

            this.breakRobot("user dead");
        }
    }

    public void move() {
        if (frontIsClear())
            super.move();
    }

    public void turnAround() {
        double speed = getSpeed();
        setSpeed(2 * speed);
        turnLeft();
        turnLeft();
        setSpeed(speed);
    }

    public void move(int nrSteps) {
        for (int i = 0; i < nrSteps; i++) {
            moveR();
        }
    }

    public boolean intersects(CustomRobot user){
        if(user.getIntersection()==this.getIntersection())
            return true;

        return false;
    }

    public void moveNorth(int nrSteps, CustomRobot robot) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (isFacingNorth()) {
                    move(nrSteps);
                    /*
                    if(intersects(robot)){
                        //StartGame.winLoseWindow(false, StartGame.frame, StartGame.userRobot);
                    }
                    */
                } else if (isFacingSouth()) {
                    turnAround();
                    move(nrSteps);
                } else if (isFacingEast()) {
                    turnLeft();
                    move(nrSteps);
                } else if (isFacingWest()) {
                    turnRight();
                    move(nrSteps);
                }
                //System.out.println(getIntersection());
            }
        });
        thread.start();
    }

    public void moveSouth(int nrSteps, CustomRobot robot) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (isFacingSouth()) {
                    move(nrSteps);
                } else if (isFacingNorth()) {
                    turnAround();
                    move(nrSteps);
                } else if (isFacingWest()) {
                    turnLeft();
                    move(nrSteps);
                } else if (isFacingEast()) {
                    turnRight();
                    move(nrSteps);
                }
                //System.out.println(getIntersection());
            }
        });
        thread.start();
    }

    @Override
    public void breakRobot(String s) {
        super.breakRobot(s);
    }

    public void moveEast(int nrSteps, CustomRobot robot) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (isFacingEast()) {
                    move(nrSteps);
                } else if (isFacingWest()) {
                    turnAround();
                    move(nrSteps);
                } else if (isFacingNorth()) {
                    turnRight();
                    move(nrSteps);
                } else if (isFacingSouth()) {
                    turnLeft();
                    move(nrSteps);
                }
            }
        });
        thread.start();

    }

    public void moveWest(int nrSteps, CustomRobot robot) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (isFacingWest()) {
                    move(nrSteps);
                } else if (isFacingEast()) {
                    turnAround();
                    move(nrSteps);
                } else if (isFacingNorth()) {
                    turnLeft();
                    move(nrSteps);
                } else if (isFacingSouth()) {
                    turnRight();
                    move(nrSteps);
                }
                //System.out.println(getIntersection());
            }
        });
        thread.start();
    }

    public boolean checkPrize(){
        if(countThingsInBackpack() > 0){
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        while(true){
            // if(StartGame.userRobot.getIntersection()==StartGame.enemyRobot.getIntersection())
            this.breakRobot("Broken Player!!!");
        }
    }
}


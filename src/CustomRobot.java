import becker.robots.*;

import java.awt.*;

import static dit948.Random.*;

/**
 * Created by Iso on 10-Nov-15.
 */
public class CustomRobot extends RobotSE {
    protected boolean isPlayer;

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

    public void go(int steps) {
        for (int i = 0; i != steps; i++) { // a potentially infinite loop
            pickThing();
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

    public void putThing() {
        if (countThingsInBackpack() > 0)
            super.putThing();
    }

    public void pickThing() {
        if (canPickThing())
            super.pickThing();
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
            move();
        }
    }

    public void moveNorth(int nrSteps) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (isFacingNorth()) {
                    move(nrSteps);
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
            }
        });
        thread.start();
    }

    public void moveSouth(int nrSteps) {
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
            }
        });
        thread.start();
    }

    public void moveEast(int nrSteps) {
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

    public void moveWest(int nrSteps) {
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
            }
        });
        thread.start();
    }
}


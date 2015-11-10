import becker.robots.*;

/**
 * Created by Iso on 10-Nov-15.
 */
public class CustomRobot extends Robot {

    public CustomRobot (City city, int xStreet, int yStreet, Direction direction) {
        super(city, xStreet, yStreet, direction);
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

}

import becker.robots.City;
import becker.robots.Direction;
import becker.robots.RobotUIComponents;

import javax.management.remote.SubjectDelegationPermission;
import javax.swing.*;

import static dit948.Random.randomInt;

/**
 * A custom robot class that's used for the CPU robot
 */
public class MachineRobot extends CustomRobot implements Runnable {
    CustomRobot userRobot;
    JFrame frame;
    RobotUIComponents components;

    public MachineRobot (City city, int xStreet, int yStreet, Direction direction,
                         CustomRobot userRobot, JFrame frame, RobotUIComponents components) {
        super(city, xStreet, yStreet, direction);
        this.userRobot = userRobot;
        this.frame = frame;
        this.components = components;
    }

    public MachineRobot (City city, int xStreet, int yStreet, Direction direction, JFrame frame, RobotUIComponents components) {
        super(city, xStreet, yStreet, direction);
        this.frame = frame;
        this.components = components;
    }

    public void move() {
        if (frontIsClear())
            super.move();
    }

    public void moveR() {
        if (frontIsClear())
            super.move();
        if (intersects()) Main.winLoseWindow(false, frame, this, components);
    }

    public void setUserRobot(CustomRobot userRobot) {
        this.userRobot = userRobot;
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
        if (this.getIntersection() == userRobot.getIntersection()) {
            userRobot.breakRobot("deal with it");
        }
    }

    public void go(int steps) {
        for (int i = 0; i != steps; i++) { // a potentially infinite loop
            randomMove();
        }
    }

    public boolean intersects(){
        if(userRobot.getIntersection()==this.getIntersection())
            return true;

        return false;
    }

    public void run() {
        System.out.println(getIntersection());
        //if (!this.isPlayer) {
        boolean b =true;
        for(int i=0;i<50000;i++) {
            go(1);
//            if(this.getIntersection()==this.userRobot.getIntersection()) {
//                StartGame.winLoseWindow(false, frame, userRobot);
//
//                //this.userRobot.breakRobot("Robot Dead!");
//            }
            if(super.breakBot == true){
                breakRobot("Broken enemy!!!");
            }
        }
    }
}

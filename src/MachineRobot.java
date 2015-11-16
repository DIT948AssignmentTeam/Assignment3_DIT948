import becker.robots.City;
import becker.robots.Direction;

/**
 * Created by iso on 2015-11-16.
 */
public class MachineRobot extends CustomRobot implements Runnable {
    public MachineRobot (City city, int xStreet, int yStreet, Direction direction, boolean isPlayer) {
        super(city, xStreet, yStreet, direction,isPlayer);
    }

    public void run() {
        if (!this.isPlayer) {
            go(50000);
        }
    }
}

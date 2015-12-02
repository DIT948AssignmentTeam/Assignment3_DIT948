/**
 * Created by Shafiq_Saloum on 12/2/2015.
 */
//latest

import becker.robots.City;
import becker.robots.Direction;
import becker.robots.Robot;
import becker.robots.Thing;
import becker.robots.icons.Icon;
import dit948.Random;

import static dit948.Random.randomInt;

/**
 * A class that represents the prize
 *
 */
public class Prize extends Thing{

    public Prize(City city, int i, int i1, Direction direction, boolean b, Icon icon) {
        super(city, i, i1, direction, b, icon);
        b = true;
    }

    public Prize(City city, int i, int i1) {
        super(city, i, i1);
    }

    public Prize(City city, int i, int i1, Direction direction) {
        super(city, i, i1, direction);
    }

    public Prize(Robot robot) {
        super(robot);
    }
}

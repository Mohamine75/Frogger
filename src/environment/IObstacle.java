package environment;

import util.Case;

public interface IObstacle {

    void addToGraphics();

    void move();

    boolean covers(Case c);
}

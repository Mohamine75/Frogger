package environment;

import util.Case;

public interface IObstacle {

    void addToGraphics();
    boolean action();
    void move();
    void setLeftPosition(Case c);
    Case getLeftPosition();
    boolean covers(Case c);
}

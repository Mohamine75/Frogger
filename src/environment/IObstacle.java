package environment;

import util.Case;

public interface IObstacle {

    void addToGraphics();

    void move();
    public void setLeftPosition(Case c);
    public Case getLeftPosition();
    boolean covers(Case c);
}

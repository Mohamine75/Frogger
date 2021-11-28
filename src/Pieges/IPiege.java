package Pieges;

import util.Case;


public interface IPiege {

    public Case getPosition();

    public void setPosition(Case position);

    public void addToGraphics();
    public boolean action();

    public boolean covers(Case c);
    public boolean forbidden(Case c);
}
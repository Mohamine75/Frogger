package Pieges;

import util.Case;


public interface IPiege {

     Case getPosition();

     void setPosition(Case position);

     void addToGraphics();
     boolean action();

     boolean covers(Case c);
     boolean forbidden(Case c);
}
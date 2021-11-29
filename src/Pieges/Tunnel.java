package Pieges;

import gameCommons.Game;
import graphicalElements.Element;
import util.Case;

import java.awt.*;

public class Tunnel implements IPiege {
    private boolean safe = true;
    private final Game game;
    private Case position;
    private final Color color = Color.yellow;
    private int length = 2;


    public Tunnel(Game game, Case position) {
        this.game = game;
        this.position = position;
    }

    @Override

    public Case getPosition() {
        return position;
    }


    @Override
    public void setPosition(Case position) {
        this.position = position;
    }


    @Override
    public void addToGraphics() {
        Color color = this.color;
        game.getGraphic().add(new Element(position.absc, position.ord, color));
        game.getGraphic().add(new Element(position.absc+1, position.ord, color));
    }

    public boolean action(){
        return true;
    }

    @Override
    public boolean covers(Case c) {
        if(c.ord == position.ord){
            for (int i = 0; i < length; i++) {
                if (position.absc + i == c.absc) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean forbidden(Case c) {
        return covers(c);
    }
}
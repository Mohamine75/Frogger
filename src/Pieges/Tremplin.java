package Pieges;

import gameCommons.Game;
import graphicalElements.Element;
import util.Case;

import java.awt.*;


public class Tremplin implements IPiege {
    private final Game game;
    private Case position;
    private final Color color =  Color.white;


    public Tremplin(Game game, Case position) {
        this.game = game;
        this.position = position;
    }

    public Case getPosition() {
        return position;
    }

    public void setPosition(Case position) {
        this.position = position;
    }

    public void addToGraphics(){
        Color color = this.color;
        game.getGraphic().add(new Element(position.absc,position.ord, color));
    }

    public boolean action(){
        game.getFrog().move(game.getFrog().getDirection());
        return true;

    }
    public boolean covers(Case c){
        if(c.ord == position.ord){
            return c.absc == position.absc;
        }
        return false;
    }
    public boolean forbidden(Case c) {
        return false;
    }
}
package Pieges;

import gameCommons.Game;
import graphicalElements.Element;
import util.Case;

import java.awt.*;

public class Bonus implements IPiege{

    private final Game game;
    private Case position;
    private int valeur;
    private final Color color =  Color.MAGENTA;


    public Bonus(Game game, Case position) {
        this.game = game;
        this.position = position;
        this.valeur = 3;
    }
    public Bonus(Game game, Case position,int valeur) {
        this.game = game;
        this.position = position;
        this.valeur = valeur;
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
        game.getFrog().setScore(valeur);
        return true;

    }
    public boolean covers(Case c){
        if(c.ord == position.ord){
            return c.absc == position.absc;
        }
        return false;
    }

    @Override
    public boolean forbidden(Case c) {
        return false;
    }
}

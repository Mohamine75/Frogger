package environment;

import gameCommons.Game;
import graphicalElements.Element;
import images.Images;
import util.Case;
import util.Direction;

import java.awt.*;
import java.util.ArrayList;

public class Log implements IObstacle{

    private final Game game;
    private final int length = 3;
    private Case leftPosition;
    private final boolean leftToRight;
    private final Images imageClass = new Images();
    private final ArrayList<Image> sprite;

   /* public Log(Game game, Case leftPosition){
        this.game = game;
        this.leftPosition = leftPosition;
        this.length = game.randomGen.nextInt(4)+2;
    }*/
    public Log(Game game,Case leftPosition,boolean leftToRight){
        this.game = game;
        this.leftPosition = leftPosition;
        this.leftToRight = leftToRight;
        this.sprite = imageClass.giveObstacle(leftToRight, length, true);
    }

    public void addToGraphics() {
        for(int i = 0; i < length; i++){
            game.getGraphic().add(new Element(leftPosition.absc+i, leftPosition.ord, sprite.get(i)));
        }
    }

    public void move(){
        if(covers(game.getFrog().getPosition())){
            actionGrenouille(false);
        }
        if(covers(game.getFrogTwo().getPosition())){
            actionGrenouille(true);
        }
        if(leftToRight) {
            this.leftPosition = new Case(leftPosition.absc + 1, leftPosition.ord);
        }

        else {
            this.leftPosition = new Case(leftPosition.absc - 1, leftPosition.ord);
        }
    }

    public void setLeftPosition(Case c){
        this.leftPosition = c;
    }

    @Override
    public Case getLeftPosition() {
        return leftPosition;
    }

    public boolean action(){
        return true;
    }
    private void actionGrenouille(boolean sndFrog){
        if (!sndFrog) {
            if (leftToRight) {
                game.getFrog().move(Direction.right);
            } else {
                game.getFrog().move(Direction.left);
            }
        }
        else{
            if (leftToRight) {
            game.getFrogTwo().move(Direction.right);
        } else {
            game.getFrogTwo().move(Direction.left);
        }
        }
    }

    public boolean covers(Case c){
        if(c.ord == leftPosition.ord){
            for (int i = 0; i < length; i++) {
                if (leftPosition.absc + i == c.absc) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean action2(){
        return true;
    }
}
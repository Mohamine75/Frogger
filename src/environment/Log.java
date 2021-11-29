package environment;

import gameCommons.Game;
import graphicalElements.Element;
import util.Case;

import java.awt.*;

public class Log implements IObstacle{

    private Game game;
    private int length;
    private Case leftPosition;

    public Log(Game game, Case leftPosition){
        this.game = game;
        this.leftPosition = leftPosition;
        this.length = game.randomGen.nextInt(4)+1;
    }

    public void addToGraphics() {
        for(int i = 0; i < length; i++){
            game.getGraphic().add(new Element(leftPosition.absc+i, leftPosition.ord, Color.CYAN));
        }
    }

    public void move(){
        this.leftPosition = new Case(leftPosition.absc-1, leftPosition.ord);
    }

    public boolean covers(Case c){
        if(c.ord == leftPosition.ord){
            for(int i = 0; i < length; i++){
                if(leftPosition.absc+i == c.absc){
                    return true;
                }
            }
        }
        return false;
    }

}

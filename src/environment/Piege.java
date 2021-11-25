package environment;
import util.Case;
import gameCommons.*;
import graphicalElements.Element;
import java.awt.*;


public class Piege {

    private final Game game;
    private Case position;
    private final Color color = Color.RED;

    public Piege(Game game, Case position) {
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
    public boolean covers(Case c){
       if(c.ord == position.ord){
           return c.absc == position.absc;
       }
       return false;
    }

    @Override
    public String toString() {
        return "Piege{" +
                "position=" + position +
                '}';
    }
}

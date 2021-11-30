package environment;

import gameCommons.Game;
import graphicalElements.Element;
import util.Case;

import java.awt.*;

public class Car implements IObstacle{

	private final Game game;
	private Case leftPosition;
	private final boolean leftToRight;
	private final int length;
	private final Color colorLtR = Color.MAGENTA;
	private final Color colorRtL = Color.GREEN;

	//Constructeur(s)

	public Car(Game game, Case leftPosition, boolean leftToRight) {
		this.game = game;
		this.leftPosition = leftPosition;
		this.leftToRight = leftToRight;
		this.length = 3;
	}
	//TODO : ajout de methodes

	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	public void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}
	public void setLeftPosition(Case c){
		this.leftPosition = c;
	}

	@Override
	public Case getLeftPosition() {
		return leftPosition;
	}

	public void move(){
		if(leftToRight) {
			/*if(this.leftPosition.absc +length -1 > game.width){
				leftPosition =  new Case(0, leftPosition.ord);
				return;
			}*/
			this.leftPosition = new Case(leftPosition.absc +1,leftPosition.ord);
		}
		else{
			this.leftPosition = new Case(leftPosition.absc - 1,leftPosition.ord);
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

}

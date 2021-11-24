package environment;

import java.awt.Color;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;
import util.Case;

public class Car {


	private Game game;



	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.BLUE;

	//TODO Constructeur(s)

	public Car(Game game, Case leftPosition, boolean leftToRight) {
		this.game = game;
		this.leftPosition = leftPosition;
		this.leftToRight = leftToRight;
		this.length =2;
	}
	public Car(Game game, Case leftPosition, boolean leftToRight, int length) {
		this.game = game;
		this.leftPosition = leftPosition;
		this.leftToRight = leftToRight;
		this.length = length;
	}
	//TODO : ajout de methodes


	public Case getLeftPosition() {
		return leftPosition;
	}

	public void setLeftPosition(Case leftPosition) {
		this.leftPosition = leftPosition;
	}

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
	public void move(){
		if(leftToRight) {
			/*if(this.leftPosition.absc +length -1 > game.width){
				leftPosition =  new Case(0, leftPosition.ord);
				return;
			}*/
			this.leftPosition = new Case(leftPosition.absc +1,leftPosition.ord);
			return;
		}
		else{this.leftPosition = new Case(leftPosition.absc - 1,leftPosition.ord);}
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

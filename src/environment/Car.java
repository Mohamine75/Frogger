package environment;

import java.awt.Color;

import util.Case;
import gameCommons.Game;
import graphicalElements.Element;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.BLUE;

	//TODO Constructeur(s)
	public Car(Game game, Case leftPosition, boolean leftToRight){
		this.leftToRight = leftToRight;
		this.leftPosition = leftPosition;
		this.game = game;
	}

	//TODO : ajout de methodes

	public int getLength(){return this.length;}
	public Case getLeftPosition(){
		return leftPosition;
	}
	public void setLength(int len){ this.length = len;}

	
	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	public void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic()
					.add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}
	public Car move(){
		if (leftToRight){
			Car c = new Car(game, new Case(getLeftPosition().absc + 1, getLeftPosition().ord),true);
			c.addToGraphics();
			return c;
		}
		Car c = new Car(game,new Case(getLeftPosition().absc-1,getLeftPosition().ord),false);
		c.addToGraphics();
		return c;
		}
	}


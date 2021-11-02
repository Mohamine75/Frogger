package environment;

import java.util.ArrayList;

import gameCommons.IEnvironment;
import environment.Environment;
import util.Case;
import gameCommons.Game;

public class Lane {
	private Game game;
	private int ord;
	private int speed;

	public ArrayList<Car> getCars() {
		return cars;
	}

	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;
	private int timer;

	// TODO : Constructeur(s)
	public Lane(Game game, boolean leftToRight, double density, int speed){
		this.game = game;
		this.leftToRight = leftToRight;
		this.density = density;
		this.speed = speed;
	}

	// TODO : truc
	public void update() {
		timer++;
		if (timer % speed == 0) {
			moveCars();
		}
		for (Car c:cars) {
			c.addToGraphics();
		}
		mayAddCar();
	}
		// TODO

		// Toutes les voitures se d�placent d'une case au bout d'un nombre "tic
		// d'horloge" �gal � leur vitesse
		// Notez que cette m�thode est appel�e � chaque tic d'horloge

		// Les voitures doivent etre ajoutes a l interface graphique meme quand
		// elle ne bougent pas

		// A chaque tic d'horloge, une voiture peut �tre ajout�e


	// TODO : ajout de methodes

	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase() 
	 */

	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
		if ( isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}

	private Case getFirstCase() {
		if (leftToRight) {
			return new Case(0, ord);
		} else
			return new Case(game.width - 1, ord);
	}

	private Case getBeforeFirstCase() {
		if (leftToRight) {
			return new Case(-1, ord);
		} else
			return new Case(game.width, ord);
	}

	public void moveCars(){
		for (Car c: cars) {
			c.move();
			}
		}
		public boolean isSafe(Case c){
			for(Car v:cars){
				if(c.equals(new Case(v.getLeftPosition().absc,v.getLeftPosition().ord))){
					return false;
				}
			}
			return true;
		}
	}

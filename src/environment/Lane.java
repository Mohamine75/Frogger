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
	public Lane(Game game, int ord,double density){
		this.game = game;
		this.density = density;
		this.ord = ord;
		this.speed =1;
	}
	public Lane(Game game,int ord){
		this.game = game;
		this.leftToRight = game.randomGen.nextBoolean();
		this.density = game.defaultDensity;
		this.speed = (game.randomGen.nextInt(game.minSpeedInTimerLoops)+1);
		this.ord = ord;
		this.timer = 0;
	}

	// TODO : truc
	public void update() {
		this.timer++;
		if (this.timer % speed == 0) {
			moveCars(true);
			mayAddCar();
			this.timer =0;
		}else{
			moveCars(false);
		}
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

	public void moveCars(boolean b){
		for (Car c: cars) {
			c.move(b);
			}
		}
		public boolean isSafe(Case c){
			for(Car v:cars){
				if(c.ord == v.getLeftPosition().ord){
					if(c.absc >= v.getLeftPosition().absc && c.absc < v.getLeftPosition().absc +v.getLength()) {
						return false;
					}
				}
			}
			return true;
		}
	}

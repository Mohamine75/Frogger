package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;

public class Lane {
	private Game game;

	public int getOrd() {
		return ord;
	}

	private ArrayList<Piege> pieges = new ArrayList<>();
	private int ord;
	private int speed;

	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}

	public ArrayList<Car> getCars() {
		return cars;
	}

	private ArrayList<Car> cars = new ArrayList<>();
	private boolean leftToRight;
	private double density;
	private int compteur = 0;

	public void setOrd(int ord) {
		this.ord = ord;
	}

	public Lane(Game game, int ord) {
		this.game = game;
		this.ord = ord;
		this.density = 0;
		this.leftToRight = true;
		this.cars = new ArrayList<Car>();
		this.speed = 0;
	}

	public Lane(Game game, int ord, int speed, boolean leftToRight, double density) {
		this.game = game;
		this.ord = ord;
		this.speed = speed;
		this.leftToRight = leftToRight;
		this.density = density;
		if (game.randomGen.nextInt(8) < 1 && pieges.isEmpty()) {
			pieges.add(new Piege(game, new Case(3, ord)));

		}
	}

	// TODO

	public ArrayList<Piege> getPieges() {
		return pieges;
	}

	// Toutes les voitures se d�placent d'une case au bout d'un nombre "tic
	// d'horloge" �gal � leur vitesse
	// Notez que cette m�thode est appel�e � chaque tic d'horloge

	// Les voitures doivent etre ajoutes a l interface graphique meme quand
	// elle ne bougent pas

	// A chaque tic d'horloge, une voiture peut �tre ajout�e

	public void update() {
		compteur++;
		if (compteur == speed) {
			for (Car c : cars) {
				c.move();
			}
			this.compteur = 0;
		}
		mayAddCar();
		for (Car c : cars) {
			c.addToGraphics();
		}
		for (Piege p :
				pieges) {
			p.addToGraphics();
		}
	}

	// TODO : ajout de methodes

	/*
	 * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase()
	 */

	/**
	 * Ajoute une voiture au d�but de la voie avec probabilit� �gale � la
	 * densit�, si la premi�re case de la voie est vide
	 */
	private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
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

	public boolean isSafe(Case c) {
		for (Car v : cars) {
			if (v.covers(c)) {
				return false;
			}
		}
		if (!pieges.isEmpty()) {
			for (Piege p : pieges) {
				if (p.covers(c)){
					return false;
				}
			}
		}
		return true;
	}
}

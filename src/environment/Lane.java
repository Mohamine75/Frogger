package environment;

import Pieges.*;
import gameCommons.Game;
import util.Case;

import java.util.ArrayList;

public class Lane {
	private final Game game;
	private int ord;
	private final int speed;
	private ArrayList<Car> cars = new ArrayList<>();
	private final boolean leftToRight;
	private final double density;
	private int compteur = 0;
	private ArrayList<IPiege> pieges = new ArrayList<>();


	public Lane(Game game, int ord) {
		this.game = game;
		this.ord = ord;
		this.density = 0;
		this.leftToRight = true;
		this.cars = new ArrayList<>();
		this.speed = 0;
	}

	public Lane(Game game, int ord, int speed, boolean leftToRight, double density) {
		this.game = game;
		this.ord = ord;
		this.speed = speed;
		this.leftToRight = leftToRight;
		this.density = density;
		if (game.randomGen.nextInt(10) == 0) {
			pieges.add(new Piege(game, new Case(game.randomGen.nextInt(game.width) - 1, ord)));
			return;
		}
		if (game.randomGen.nextInt(10) == 1 && pieges.isEmpty()) {
			pieges.add(new Tremplin(game, new Case(game.randomGen.nextInt(game.width) - 1, ord)));
		}
		if (game.randomGen.nextInt(20) == 2) {
			pieges.add(new Bonus(game, new Case(game.randomGen.nextInt(game.width) - 1, ord)));
		}
		if (game.randomGen.nextInt(15) == 3) {
			pieges.add(new Tunnel(game, new Case(game.randomGen.nextInt(game.width) - 1, ord)));
		}
	}

	public int getOrd() {
		return ord;
	}

	public void setOrd(int ord) {
		this.ord = ord;
	}

	public ArrayList<IPiege> getPieges() {
		return pieges;
	}

	public ArrayList<IPiege> getTremplins() {
		return pieges;
	}

	public ArrayList<Car> getCars() {
		return cars;
	}

	public boolean forbidden(Case c) {
		for (IPiege p : pieges) {
			if (p.forbidden(c)) {
				return true;

			}
		}
		return false;
	}
	public void update() {
		compteur++;
		if (compteur == speed) {
			for (Car c : cars) {
				c.move();
			}
			this.compteur = 0;
		}
		mayAddCar();
		for (IPiege p : pieges) {
			p.addToGraphics();
		}
		for (Car c : cars) {
			c.addToGraphics();
		}
	}

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
			for (IPiege p : pieges) {
				if (p.covers(c)) {
					pieges.remove(p);
					return (p.action());
				}
			}
		}

		return true;
	}
}

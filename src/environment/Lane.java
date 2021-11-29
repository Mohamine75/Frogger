package environment;

import Pieges.*;
import gameCommons.Game;
import graphicalElements.Element;
import util.Case;

import java.util.ArrayList;
import java.awt.Color;

public class Lane {
	private final Game game;
	private int ord;
	private final int speed;
	//private ArrayList<Car> cars = new ArrayList<>();
	//private ArrayList<Log> logs = new ArrayList<>();
	private ArrayList<IObstacle> obstacles = new ArrayList<>();
	private final boolean leftToRight;
	private double density;
	private int compteur = 0;
	private ArrayList<IPiege> pieges = new ArrayList<>();
	private boolean isRoad;


	public Lane(Game game, int ord) {
		this.game = game;
		this.ord = ord;
		//this.density = 0;
		this.leftToRight = true;
		//this.cars = new ArrayList<>();
		this.speed = 0;
	}

	public Lane(Game game, int ord, int speed, boolean leftToRight, double density, boolean isRoad) {
		this.game = game;
		this.ord = ord;
		this.speed = speed;
		this.leftToRight = leftToRight;
		this.density = density;
		this.isRoad = isRoad;


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
			for (IObstacle obs : obstacles) {
				obs.move();
			}
			this.compteur = 0;
		}
		paintWindow();
		//mayAddCar();
		mayAddObstacle();
		for (IObstacle obs : obstacles) {
			obs.addToGraphics();
		}
		for (IPiege p : pieges) {
			p.addToGraphics();
		}
	}

	/*private void mayAddCar() {
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density && getBeforeFirstCase().ord < (game.height-1)/2) {
				cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
			}
		}
	}*/

	/*private void mayAddLog(){
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if(game.randomGen.nextDouble() < density && getBeforeFirstCase().ord > (game.height-1)/2) {
				logs.add(new Log(game, getBeforeFirstCase()));
			}
		}
	}*/

	private void mayAddObstacle(){
		if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				if (getBeforeFirstCase().ord > (game.height - 1) / 2)
					obstacles.add(new Log(game, getBeforeFirstCase()));
				else if (getBeforeFirstCase().ord < (game.height - 1) / 2)
					obstacles.add(new Car(game, getBeforeFirstCase(), leftToRight));
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
		for (IObstacle obs : obstacles) {
			if (obs.covers(c)) {
				return false;
			}
		}
		if (!pieges.isEmpty()) {
			for (IPiege p : pieges) {
				if (p.covers(c)) {
					//pieges.remove(p);
					return (p.action());
				}
			}
		}
		return true;
	}

	public void paintWindow() {
		Color color = Color.blue;
		for (int i = 0; i < game.width; i++) {
			if ((game.height - 1) / 2 < ord)
				game.getGraphic().add(new Element(i, ord, color));

			else if ((game.height-1) /2 > ord){
				game.getGraphic().add(new Element(i, ord, Color.BLACK));
			}
		}
	}
}

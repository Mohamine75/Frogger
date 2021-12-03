package environment;

import Pieges.*;
import gameCommons.Game;
import graphicalElements.Element;
import images.Images;
import util.Case;

import java.util.ArrayList;

public class Lane {
	private final Game game;
	private int ord;
	private final int speed;
	private final ArrayList<IObstacle> obstacles = new ArrayList<>();
	private final boolean leftToRight;
	private double density;
	private int compteur = 0;
	private final ArrayList<IPiege> pieges = new ArrayList<>();
	private final boolean isRoad;
	private  boolean isGrass = false;

	public Lane(Game game, int ord) {
		this.game = game;
		this.ord = ord;
		this.leftToRight = true;
		this.speed = 0;
		this.isRoad= true;
		this.isGrass = true;
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
		if (game.randomGen.nextInt(15) == 3 && isRoad) {
			pieges.add(new Tunnel(game, new Case(game.randomGen.nextInt(game.width) - 1, ord)));
		}
	}
	public Lane(Game game, int ord, int speed, boolean leftToRight, double density) {
		int speed1;
		this.game = game;
		this.ord = ord;
		speed1 = speed;
		this.leftToRight = ord%2==0;
		this.density = density;
		this.isRoad = game.randomGen.nextInt(10) != 1;
		if(!this.isRoad){
			this.density = 0.15;
			speed1 = 4;
		}
		this.speed = speed1;
		if (game.randomGen.nextInt(10) == 0) {
			pieges.add(new Piege(game, new Case(game.randomGen.nextInt(game.width) - 1, ord)));
		}
		if (game.randomGen.nextInt(10) == 1 && pieges.isEmpty()) {
			pieges.add(new Tremplin(game, new Case(game.randomGen.nextInt(game.width) - 1, ord)));
		}
		if (game.randomGen.nextInt(20) == 2) {
			pieges.add(new Bonus(game, new Case(game.randomGen.nextInt(game.width) - 1, ord)));
		}
		if (game.randomGen.nextInt(15) == 3 && isRoad) {
			pieges.add(new Tunnel(game, new Case(game.randomGen.nextInt(game.width) - 1, ord)));
		}
		if(!isRoad){
			pieges.add(new Bonus(game, new Case(game.randomGen.nextInt(game.width) - 1, ord),8));
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

	public ArrayList<IObstacle> getObstacles() {
		return obstacles;
	}

	public ArrayList<IPiege> getPieges() {
		return pieges;
	}

	public void update() {
		compteur++;
		paintWindow();
		if (compteur == speed) {
			for (IObstacle obs : obstacles) {
				obs.move();
			}
			this.compteur = 0;
		}
		mayAddObstacle();
		for (IPiege p : pieges) {
			p.addToGraphics();
		}
		for (IObstacle obs : obstacles) {
			obs.addToGraphics();
		}
		for (IPiege p : pieges) {
			if( p instanceof Tunnel ) {
				p.addToGraphics();
			}
		}
	}


	private void mayAddObstacle(){
		if (isSafeAdd(getFirstCase()) && isSafeAdd(getBeforeFirstCase())) {
			if (game.randomGen.nextDouble() < density) {
				if (!isRoad) {
					obstacles.add(new Log(game, getFirstCase(),leftToRight));
				}else {
					obstacles.add(new Car(game, getBeforeFirstCase(), leftToRight));
				}

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

	public boolean isSafeAdd(Case c) {
		if (!pieges.isEmpty()) {
			for (IPiege p : pieges) {
				if (p.covers(c)) {
					return false;
				}
			}
		}
		for (IObstacle obs : obstacles ) {
			if((obs.covers(c))){
				return false;
			}
		}
		return true;
	}

	public boolean isSafeFrog(Case c) {
		if(c.ord != ord){
			return true;
		}
			for (IPiege p : pieges) {
				if (p.covers(c)) {
					pieges.remove(p);
					return p.action();
				}
			}
		for (IObstacle obs : obstacles ) {
			if(obs.covers(c)){
				return obs.action();
			}
		}
		return isRoad;
	}
	public boolean isSafeFrog2(Case c) {
		if(c.ord != ord){
			return true;
		}
		for (IPiege p : pieges) {
			if (p.covers(c)) {
				pieges.remove(p);
				System.out.println("Piege");
				return (p.action());
			}
		}
		for (IObstacle obs : obstacles ) {
			if(obs.covers(c)){
				return obs.action2();
			}
		}
		return isRoad;
	}

	public void paintWindow() {
		for (int i = 0; i < game.width; i++) {
			if (!isRoad)
				game.getGraphic().add(new Element(i, ord, Images.water));
			if(isRoad){
				game.getGraphic().add(new Element(i, ord, Images.road));
			}
			if(isGrass){
				game.getGraphic().add(new Element(i, ord, Images.grassImage));
			}
		}
	}
}

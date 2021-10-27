package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class Frog implements IFrog {

	private Case position;
	private Direction direction;
	private Game game;
	private IFrog frog;


	public Frog(Game game){
		this.position = new Case(game.width/2,0 );
		this.game = game;
	}

	@Override
	public Case getPosition() {
		return position;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void move(Direction key) {
		if(key == direction){
				switch (direction){
					case right:
						this.position = new Case(position.absc+1, position.ord);
						// Verif si en dehors de l'ecran
						break;
					case left:
						this.position = new Case(position.absc-1, position.ord);
						break;
					case up:
						this.position = new Case(position.absc, position.ord+1);
						break;
					case down:
						this.position = new Case(position.absc, position.ord-1);
						break;
			}
		}
		this.direction = key; // on veut faire comme a l'ancienne avec une tete qui tourne.
	}
}

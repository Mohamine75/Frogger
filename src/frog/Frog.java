/*package frog;

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

	public void move(Direction key){
		switch (key){
			case right:
				if(position.absc+1 <= game.width) {
					this.position = new Case(position.absc + 1, position.ord);
				}
				// Verif si en dehors de l'ecran
				break;
			case left:
				if(position.absc-1 >= 0) {
					this.position = new Case(position.absc - 1, position.ord);
				}
				break;
			case up:
				if(position.ord+1 <= game.height) {
					this.position = new Case(position.absc, position.ord + 1);
				}
				break;
			case down:
				if(position.ord-1 >= 0) {
					this.position = new Case(position.absc, position.ord - 1);
				}
				break;
		}
	}
}*/

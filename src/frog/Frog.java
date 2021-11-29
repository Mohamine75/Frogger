package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class Frog implements IFrog {

	private Case position;
	private Direction direction = Direction.up;
	private Game game;
	private IFrog frog;
	private int score;


	public Frog(Game game){
		this.position = new Case(game.width/2,0 );
		this.game = game;
		this.score = 0;
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
    public Integer getScoreMax() {
        return null;
    }


	public void setScore(int score) {
		this.score += score;
	}

	@Override
    public void setPosition(Case position) {

    }

    @Override
    public Integer getScore() {
        return score;
    }

    @Override

	public void move(Direction key){
		switch (key){
			case right:
				this.direction = Direction.right;
				if(!(new Case(position.absc+1,position.ord).isForbidden)){
					if(position.absc+1 <= game.width) {
					this.position = new Case(position.absc + 1, position.ord);
					}
				}
				// Verif si en dehors de l'ecran
				break;
			case left:
				this.direction = Direction.left;
				if(!(new Case(position.absc-1,position.ord).isForbidden)){
					if(position.absc-1 >= 0) {
						this.position = new Case(position.absc - 1, position.ord);
					}
				}
				break;
			case up:
				this.direction = Direction.up;
				if(!(new Case(position.absc,position.ord+1).isForbidden)){
					score ++;
					if(position.ord+1 <= game.height) {
						this.position = new Case(position.absc, position.ord + 1);
					}
				}
				break;
			case down:
				if(!(new Case(position.absc,position.ord-1).isForbidden)){
					if(score >0){
						score --;
					}
					this.direction = Direction.down;
					if(position.ord-1 >= 0) {
						this.position = new Case(position.absc, position.ord - 1);
					}
				}
				break;
		}
	}


}

package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import util.Case;
import util.Direction;

public class FrogInf implements  IFrog{
        private Case position;
        private Direction direction;
        private Game game;
        private IFrog frog;
        private Integer score;
        private Integer scoreMax;

        public FrogInf(Game game){
            this.position = new Case(game.width/2,2 );
            this.game = game;
            this.score = 0;
            this.scoreMax = 0;
            direction = Direction.up;
        }

        public Case getPosition() {
            return position;
        }

        public Direction getDirection() {
            return direction;
        }

	/* Alternatif avec mouvement de tête
	public void move(Direction key) {
		if(key == direction){
				switch (direction){
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
						if(position.absc+1 <= game.height) {
							this.position = new Case(position.absc, position.ord - 1);
						}
						break;
			}
		}
		this.direction = key; // on voulait faire comme a l'ancienne avec une tete qui tourne
	}*/

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
                        this.score++;
                        if(score > scoreMax){
                            scoreMax = score;
                        }
                        if(score<2){
                            position= new  Case(position.absc,position.ord+1);
                            game.getEnvironment().add();
                        }else {
                            //position =  new Case(position.absc,position.ord+1);
                            game.getEnvironment().decalageDown();
                            game.getEnvironment().add();
                        }
                    break;
                case down:
                    if(position.ord-1 >= 0) {
                        this.position = new Case(position.absc, position.ord - 1);
                        this.score--;
                    }
                    break;
            }
        }

    public Integer getScore() {
        return score;
    }
}


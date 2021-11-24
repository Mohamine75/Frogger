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
            this.position = new Case(game.width/2,1 );
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

    public Integer getScoreMax() {
        return scoreMax;
    }

    public void move(Direction key) {
        switch (key) {
            case right:
                if (position.absc + 1 <= game.width) {
                    this.position = new Case(position.absc + 1, position.ord);
                }
                // Verif si en dehors de l'ecran
                break;
            case left:
                if (position.absc - 1 >= 0) {
                    this.position = new Case(position.absc - 1, position.ord);
                }
                break;
            case up:
                this.score++;
                if (score > scoreMax) {
                    scoreMax = score;
                }
                game.getEnvironment().decalageDown();
                break;

        case down:
            if (score >= 1) {
            game.getEnvironment().decalageUp();
                }
            if(score>0){
                this.score--;
            }

                        break;
            }

        }

    public Integer getScore() {
        return score;
    }
}


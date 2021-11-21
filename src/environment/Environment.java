package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
    Game game;
    private ArrayList<Lane> lanes = new ArrayList<Lane>();
	//TODO
    public Environment(Game game){
        this.game = game;
        this.lanes= new ArrayList<>();
        lanes.add(new Lane(game,0));
        for (int i = 0; i < game.height -1; i++) {
            lanes.add(new Lane(game, i, 2, true, game.defaultDensity));
        }
        lanes.add(new Lane(game, game.height));
    }

        public boolean isWinningPosition(Case c){
            return c.ord == game.height;
        }

    public boolean isSafe(Case c){
        for(Lane l : lanes){
            if (!l.isSafe(c)) return false;
        }
        return true;
    }
    public void update () {
        for (Lane l : lanes) {
            l.update();
        }
    }
}

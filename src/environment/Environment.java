package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class Environment implements IEnvironment {
    private Game game;
    private ArrayList<Lane> road;

	//TODO
    public Environment(Game game){
        this.game = game;
    }

    public boolean isSafe(Case c){
        //TODO
        return true;
    }

    public boolean isWinningPosition(Case c) {
        return false;
    }

    public void update(){}
}

package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.*;

public class Environment implements IEnvironment {
	private Game game;
	private ArrayList<Lane>lanes;

	public Environment(Game game) {
		this.game = game;
		this.lanes = new ArrayList<>();
		this.lanes.add(new Lane(game,0,0.0D));
		for (int i = 1; i < game.height -1; i++) {
			lanes.add(new Lane(game,i));
		}
	}


	public boolean isSafe(Case c)
	{
			return (this.lanes.get(c.ord).isSafe(c));
	}

	public boolean isWinningPosition(Case c){

		return c.ord == this.game.height - 1;
	}

	@Override
	public void update() {
		for(Lane l: lanes){
			l.update();
		}
	}
}

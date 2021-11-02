package environment;

import java.util.ArrayList;

import util.Case;
import gameCommons.*;

public class Environment implements IEnvironment {
	private Game game;
	private ArrayList<Lane>lanes;

	public Environment(Game game) {
		this.game = game;
		this.lanes = new ArrayList<>(game.height);
	}

	public boolean isSafe(Case c){
		Case temp= new Case(game.getFrog().getPosition().absc,game.getFrog().getPosition().ord);
		if(lanes.size() !=0) {
			if (lanes.get(c.ord).isSafe(c)) {
				return true;
			}
		}
		return false;
	}

	public boolean isWinningPosition(Case c){
		return isSafe(game.getFrog().getPosition()) && game.getFrog().getPosition().ord == game.height;
	}

	@Override
	public void update() {
		for(Lane l: lanes){
			l.update();
		}
	}
}

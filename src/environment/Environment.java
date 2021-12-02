package environment;

import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Case;

import java.util.ArrayList;

public class Environment implements IEnvironment {
    private final Game game;
    private ArrayList<Lane> lanes;

    //TODO
    public Environment(Game game) {
        this.game = game;
        this.lanes = new ArrayList<>();
        lanes.add(new Lane(game, 0));
        for (int i = 1; i < game.height - 1; i++) {
            if(i < ((game.height-1)/2 ))
                lanes.add(new Lane(game, i, game.randomGen.nextInt(10)+1,game.randomGen.nextBoolean() , 0.01, true));
            else {
                lanes.add(new Lane(game, i, game.randomGen.nextInt(10)+1,game.randomGen.nextBoolean(), 0.04, false));
            }
        }
        lanes.add(new Lane(game, game.height-1));
    }

    public boolean isWinningPosition(Case c) {
        return c.ord == lanes.size() - 1;
    }

    public boolean isSafe(Case c) {
        for (Lane l : lanes) {
            if (!l.isSafeFrog(c)) return false;
        }
        return true;
    }
    public boolean isSafe2(Case c) {
        for (Lane l : lanes) {
            if (!l.isSafeFrog2(c)) return false;
        }
        return true;
    }

    public void update() {
        for (Lane l : lanes) {
            l.update();
        }
    }

    @Override
    public Lane getLane(int ord) {
        return lanes.get(ord);
    }

    public void add(){}
    public void decalageDown() {}
    public void decalageUp() {}
}
package environment;

import java.lang.reflect.Array;
import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Direction;


public class EnvInf implements IEnvironment {
    private final Game game;
    private ArrayList<Lane> lanes;

    public EnvInf(Game game) {
        this.game = game;
        this.lanes = new ArrayList<>();
        lanes.add(new Lane(game, 0));
        lanes.add(new Lane(game, 1));
        for (int i = 2; i < game.height + 5; i++) {
            lanes.add(new Lane(game, i, game.randomGen.nextInt(5) + 1, game.randomGen.nextBoolean(), 0.01));
        }
        System.out.println(toString());
    }



    @Override
    public String toString() {
        String res = "lanes : ";
        for (Lane l :
                lanes) {
            res += l.getOrd();
        }
        return res;
    }


    //TODO


    public void decalageDown(){
        for (Lane l: lanes) {
            l.setOrd(l.getOrd()-1);
            for(Car v: l.getCars()){
                v.setLeftPosition(new Case(v.getLeftPosition().absc,v.getLeftPosition().ord-1));
            }
        }
        add();
    }



    public void decalageUp() {
        for (Lane l: lanes) {
            l.setOrd(l.getOrd()+1);
            for(Car v: l.getCars()){
                v.setLeftPosition(new Case(v.getLeftPosition().absc,v.getLeftPosition().ord+1));
            }
        }
    }
    public boolean isSafe(Case c) {
        for (Lane l : lanes) {
            if (!l.isSafe(c)) return false;
        }
        return true;
    }

    public ArrayList<Lane> getLanes() {
        return lanes;
    }

    public void add() {
        lanes.add(new Lane(game, lanes.size(), game.randomGen.nextInt(10) + 1, game.randomGen.nextBoolean(), 0.1));
    }

    public Lane getLane(int ord) {
        return lanes.get(ord);
    }

    public boolean isWinningPosition(Case c) {
        return false;
    }

    public void update() {
        for (Lane l : lanes) {
            l.update();
        }

    }

}

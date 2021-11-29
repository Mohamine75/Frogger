/*package environment;

import java.util.ArrayList;

import Pieges.Piege;
import Pieges.*;
import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;


public class EnvInf implements IEnvironment {
    private final Game game;
    private final ArrayList<Lane> lanes;

    public EnvInf(Game game) {
        this.game = game;
        this.lanes = new ArrayList<>();
        lanes.add(new Lane(game, 0));
        lanes.add(new Lane(game, 1));
        for (int i = 2; i < game.height + 5; i++) {
            lanes.add(new Lane(game, i, game.randomGen.nextInt(5 )+1, game.randomGen.nextBoolean(), 0.01));
        }
        System.out.println(this);
    }

    public ArrayList<Lane> getLanes() {
        return lanes;
    }

    public void add() {
        lanes.add(new Lane(game, lanes.get(lanes.size()-1).getOrd()+1,
                game.randomGen.nextInt(10 )+1, game.randomGen.nextBoolean(), 0.01));
    }

    public Lane getLane(int ord) {
       for(Lane l : lanes){
           if (l.getOrd()== ord){
               return l;
           }
       }
       return null;
    }

    public boolean isWinningPosition(Case c) {
        return false;
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


    public void decalageDown(){

        for (Lane l: lanes) {
            l.setOrd(l.getOrd()-1);
            for (Car v : l.getCars()) {
                v.setLeftPosition(new Case(v.getLeftPosition().absc, v.getLeftPosition().ord - 1));
            }
            if(!l.getPieges().isEmpty()) {
                for (IPiege p : l.getPieges()) {
                    p.setPosition(new Case(p.getPosition().absc, p.getPosition().ord - 1));
                }
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
            for(IPiege p : l.getPieges()){
                p.setPosition(new Case(p.getPosition().absc,p.getPosition().ord+1));
            }
        }
    }

    public boolean isSafe(Case c) {
        for (int i = 0; i < lanes.size(); i++) {
            if (!lanes.get(i).isSafe(c)) return false;
        }
        return true;
    }



    public void update() {
        for (Lane l : lanes) {
            l.update();
        }

    }

}
*/
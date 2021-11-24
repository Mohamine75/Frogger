package environment;

import java.lang.reflect.Array;
import java.util.ArrayList;

import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Direction;


public class EnvInf implements IEnvironment {
    private final Game game;
    private boolean down = false;
    ArrayList<Lane> erased = new ArrayList<>();
    private ArrayList<Lane> lanes;

    public EnvInf(Game game) {
        this.game = game;
        this.lanes = new ArrayList<>();
        lanes.add(new Lane(game, 0));
        lanes.add(new Lane(game, 1));
        for (int i = 2; i < game.height + 5; i++) {
            lanes.add(new Lane(game, i, game.randomGen.nextInt(10) + 1, game.randomGen.nextBoolean(), 0.04));
        }
        System.out.println(toString());
    }

    public void setDown(boolean down) {
        this.down = down;
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


    /*public void decalageDown(){
         erased.add(lanes.get(0));
         lanes.remove(0);
        for (Lane l : lanes) {
            l.setOrd(lanes.indexOf(l));
        }
         System.out.println(toString());
    }*/

    public void decalageDown() {
        erased.add(lanes.get(0));
        for (int i = 0; i < lanes.size() - 1; i++) {
            ArrayList<Car> voitures = lanes.get(i + 1).getCars();
            for (Car v:
                 voitures) {
                v.setLeftPosition(new Case(v.getLeftPosition().absc,v.getLeftPosition().ord-1));
            }

            lanes.set(i, lanes.get(i + 1));
            lanes.get(i).setCars(voitures);
            lanes.get(i).setOrd(i);
        }
        lanes.remove(lanes.size() - 1);
        add();
        game.update();
        System.out.println(toString());
    }

    public void decalageUp() {
        for (int i = 1; i < lanes.size() - 1; i++) {
            ArrayList<Car> voitures = lanes.get(i  -1).getCars();
            for (Car v:
                    voitures) {
                v.setLeftPosition(new Case(v.getLeftPosition().absc,v.getLeftPosition().ord+1));
            }

            lanes.set(i, lanes.get(i -1));
            lanes.get(i).setCars(voitures);
            lanes.get(i).setOrd(i);
        }
        erased.remove(0);
        add();
        game.update();
        System.out.println(toString());


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
        lanes.add(new Lane(game, lanes.size() - 1, game.randomGen.nextInt(10) + 1, game.randomGen.nextBoolean(), 0.1));
    }

    public Lane getLane(int ord) {
        return lanes.get(ord);
    }

    public boolean isWinningPosition(Case c) {
        return false;
    }

    public void update() {
        int compteur = 0;
       /* if(down){
            decalageDown();
            down = false;
        }*/
        for (Lane l : lanes) {
            l.update();
        }
       /* }
        if(game.getFrog().getScore() > compteur){
            compteur=game.getFrog().getScore();
            decalageDown();
        }
        if (game.getFrog().getScore()<compteur){
            decalageUp();
        }*/
    }

}

package environment;

import java.util.ArrayList;

import frog.FrogInf;
import util.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import util.Direction;

public class EnvInf implements  IEnvironment{
    private final Game game;
    private ArrayList<Lane> lanes;
    private FrogInf frog;

    //TODO
    public EnvInf(Game game){
        this.game = game;
        this.lanes= new ArrayList<>();
        lanes.add(new Lane(game,0));

        for (int i = 1; i < game.height -1; i++) {
            lanes.add(new Lane(game, i, game.randomGen.nextInt(10)+1,game.randomGen.nextBoolean() , 0.2));
        }
    }

    public boolean isSafe(Case c){
        for(Lane l : lanes){
            if (!l.isSafe(c)) return false;
        }
        return true;
    }

    public ArrayList<Lane> getLanes() {
        return lanes;
    }

   public void add(){
        lanes.add(new Lane(game, lanes.size(), game.randomGen.nextInt(10)+1,game.randomGen.nextBoolean() , 0.1));
   }

   /*public void addGraphicLane(){
        if(frog.getDirection() == Direction.up)
            game.getGraphic().add(new Lane(game, lanes.size(), game.randomGen.nextInt(10)+1,game.randomGen.nextBoolean() , 0.1) );
   }*/

   public boolean isWinningPosition(Case c){
        return false;
   }

    public void update () {
        for (Lane l : lanes) {
            l.update();
        }
    }

}

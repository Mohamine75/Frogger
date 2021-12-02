package gameCommons;

import util.Case;
import util.Direction;

public interface IFrog {
	
	/**
	 * Donne la position actuelle de la grenouille
	 * @return
	 */
    Case getPosition();
	
	/**
	 * Donne la direction de la grenouille, c'est � dire de son dernier mouvement 
	 * @return
	 */
    Direction getDirection();
	Integer getScoreMax();
	void setPosition(Case position);
	Integer getScore();
	/**
	 * D�place la grenouille dans la direction donn�e et teste la fin de partie
	 * @param key
	 */
    void move(Direction key);

	void setScore(int bonus);
}

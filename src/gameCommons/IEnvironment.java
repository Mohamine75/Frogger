package gameCommons;

import util.Case;
import environment.Lane;
import java.util.ArrayList;
public interface IEnvironment {

	/**
	 * Teste si une case est sure, c'est � dire que la grenouille peut s'y poser
	 * sans mourir
	 * 
	 * @param c
	 *            la case � tester
	 * @return vrai s'il n'y a pas danger
	 */
	public boolean isSafe(Case c);

	public void add();
	public void decalageDown();
	public void decalageUp();
	public void setDown(boolean down);
	public String toString();
	public ArrayList<Lane> getLanes();

	public Lane getLane(int ord);
	/**
	 * Teste si la case est une case d'arrivee
	 * 
	 * @param c
	 * @return vrai si la case est une case de victoire
	 */
	//public boolean isWinningPosition(Case c);

	/**
	 * Effectue une �tape d'actualisation de l'environnement
	 */
	public void update();

}

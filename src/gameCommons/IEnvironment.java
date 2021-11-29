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
    boolean isSafe(Case c);

	void add();
	void decalageDown();
	void decalageUp();
	String toString();
	ArrayList<Lane> getLanes();

	Lane getLane(int ord);
	/**
	 * Teste si la case est une case d'arrivee
	 * 
	 * @param c
	 * @return vrai si la case est une case de victoire
	 */
	public boolean isWinningPosition(Case c);

	/**
	 * Effectue une �tape d'actualisation de l'environnement
	 */
    void update();

}

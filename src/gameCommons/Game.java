package gameCommons;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

import java.awt.*;
import java.util.Random;

//import frog.Frog;

public class Game {

	public final Random randomGen = new Random();
	// Caracteristique de la partie
	public final int width;
	private float timer = 0;
	public  int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;
	private IEnvironment environment;
	private IFrog frog;
	private final IFroggerGraphics graphic;

	/**
	 * 
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant d�placement
	 * @param defaultDensity
	 *            densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
	}

	public IFrog getFrog() {
		return frog;
	}

	public IEnvironment getEnvironment() {
		return environment;
	}
	/**
	 * Lie l'objet frog � la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	public float getTimer() {
		return timer;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	public boolean testLose() {
		// TODO
		if(!environment.isSafe(frog.getPosition())){
			graphic.endGameScreen("Perdu , score :" + frog.getScore()
			+"\n Meilleur score: " +frog.getScoreMax() +  " " + timer);
			return true;
		}
		return false;
	}

	public boolean testWin() {
		if(environment.isWinningPosition(frog.getPosition())){
			graphic.endGameScreen("Gagné !!");
			return true;
		}
		return false;
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */

	public void update() {
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition(), Color.GREEN));
		timer+=0.1;
		testLose();
		//testWin();
	}

}

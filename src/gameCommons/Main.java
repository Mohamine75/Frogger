package gameCommons;

import environment.EnvInf;
import frog.FrogInf;
import graphicalElements.FroggerGraphic;
import graphicalElements.IFroggerGraphics;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

//import frog.Frog;

//import environment.Environment;
//import frog.Frog;
//import environment.Environment;

public class Main {

	public static void main(String[] args)throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		//Caract�ristiques du jeu
		int width = 26;
		int height = 20;
		int tempo = 100;
		int minSpeedInTimerLoops = 3;
		double defaultDensity = 0.01;
		//Cr�ation de l'interface graphique
		IFroggerGraphics graphic = new FroggerGraphic(width, height);
		//Cr�ation de la partie
		Game game = new Game(graphic, width, height, minSpeedInTimerLoops, defaultDensity);
		//Cr�ation et liason de la grenouille
		IFrog frog = new FrogInf(game);

		game.setFrog(frog);
		graphic.setFrog(frog);

		//IFrog frog1 = new Frog(game);
		//game.setFrogTwo(frog1);
		//graphic.setFrogTwo(frog1);
		//Cr�ation et liaison de l'environnement
		IEnvironment env = new EnvInf(game);
		game.setEnvironment(env);
		//Boucle principale : l'environnement s'acturalise tous les tempo milisecondes
		File file = new File("src/Music/Doom.wav");
		File file2 = new File("src/Music/Gta.wav");
		File file3 = new File("src/Music/Naruto.wav");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
		clip.start();
		AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(file2);
		Clip clip2 = AudioSystem.getClip();
		clip2.open(audioStream2);
		AudioInputStream audioStream3 = AudioSystem.getAudioInputStream(file3);
		Clip clip3 = AudioSystem.getClip();
		clip3.open(audioStream3);

		Timer timer = new Timer(tempo, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.update();
				graphic.repaint();
				if(game.testLose()){
					clip.stop();
					clip3.start();
					}
				}
		});
		timer.setInitialDelay(0);
		timer.start();

	}
}

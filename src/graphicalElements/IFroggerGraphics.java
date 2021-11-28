package graphicalElements;
import util.Case;
import gameCommons.IFrog;

public interface IFroggerGraphics {
	
	/**
	 * Ajoute l'�l�ment aux �l�ments � afficher
	 * @param e
	 */
    void add(Element e);
    
    /**
     * Enl�ve tous les �l�ments actuellement affich�s
     */
    void clear();
    
    /***
     * Actualise l'affichage
     */
    void repaint();


    /**
     * Lie la grenouille � l'environneemnt graphique
     * @param frog
     */
    void setFrog(IFrog frog);
    
    /**
     * Lance un �cran de fin de partie
     * @param message le texte � afficher
     */
    void endGameScreen(String message);
}

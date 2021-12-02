package graphicalElements;

import util.Case;

import java.awt.*;


public class Element extends Case {
    //public final Color color;
    public Color color;
    public Image image;

    public Element(int absc, int ord, Image image) {
        super(absc, ord);
        this.image = image;
    }

    public Element(Case c, Image image) {
        super(c.absc, c.ord);
        this.image = image;
    }

}

package packageForme;
import packageCalcul.Form;


public class Cercle implements Form {
    
    private Point centre;
    private double rayon;

    public Cercle(Point centre, double rayon) {
        this.centre = centre;
        this.rayon = rayon;
    }

    public double getRayon() {
        return rayon;
    }

    public double calsurface() {
        return Math.PI * this.rayon * this.rayon;
    }

    public double calculeDiametre() {
        return this.rayon * 2;
    }

    public double calcirconference() {
        return Math.PI * this.rayon * 2;
    }
}

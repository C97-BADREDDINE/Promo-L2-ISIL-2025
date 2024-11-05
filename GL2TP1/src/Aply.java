
import packageCalcul.Calcul;
import packageForme.Cercle;
import packageForme.Point;

public class Aply {

    public static void main(String[] args) {

        // Create a point
        Point p = new Point(2, 3);

        // Create a circle
        Cercle c = new Cercle(p, 5);

        // Create a calculator
        Calcul calc = new Calcul();

        // Display the circle
        calc.affiche(c);
    }
}

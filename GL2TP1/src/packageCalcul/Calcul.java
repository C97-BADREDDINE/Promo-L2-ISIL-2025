package packageCalcul;

import packageForme.Cercle;

public class Calcul {
    public void affiche(Cercle c) {
        System.out.println("surface:"+c.calsurface());
		System.out.println("conference:"+c.calcirconference());
        System.out.println("diametre:"+c.calculeDiametre());
    }
}
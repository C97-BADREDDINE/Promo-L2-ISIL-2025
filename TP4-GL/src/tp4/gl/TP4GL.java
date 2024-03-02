
package tp4.gl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TP4GL {

    public static void main(String[] args) throws ParseException{

        //Piece -----------------------------------------------------------------
        Piece p1 = new Piece(1, "roue", 4, 4, 50);
        System.out.println(p1);

        Piece p2 = new Piece(2, "moteur", 1, 1, 100);
        System.out.println(p2);

        Equipement e1 = new Equipement();
        System.out.println(e1.getPrice());

        //Planning -----------------------------------------------------------------
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");

        Planning p = new Planning();
        p.ajouterMaint(date.parse("22/3/2004"), 2, "changement de roue");
        p.ajouterMaint(date.parse("16/7/2009"), 3, "changement de moteur");
        p.afficherListMaint();

        //p.modifierMaint(date.parse("22/3/2004"), 3, "changement de moteur");
        //p.afficherListMaint();

        p.supprimerMaint(date.parse("22/3/2004"));
        p.afficherListMaint();

        Planning Pl = new Planning();
        Pl.afficherListMaint();

    }
    
}


package tp4.gl;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        // Piece -----------------------------------------------------------------
        System.out.println("\n\t---------------- Piece ----------------\n");
        Piece p1 = new Piece(1, "roue", 4, 4, 50);
        System.out.println(p1);

        Piece p2 = new Piece(2, "moteur", 1, 1, 100);
        System.out.println(p2);

        // Planning -----------------------------------------------------------------
        System.out.println("\n\t---------------- Planning ----------------\n");
        Planning p = new Planning(1);
        p.ajouterMaint(new Date(110, 2, 11), 2, "changement de roue");
        p.ajouterMaint(new Date(113, 6, 6), 3, "changement de moteur");
        p.afficherListMaint();


        System.out.println("\n  ---------------- apres supprimerMaint date= 2013 ----------------\n");
        p.supprimerMaint(new Date(113, 6, 6));
        p.afficherListMaint();

        Planning Pl = new Planning(2);
        Pl.ajouterMaint(new Date(124, 07, 30), 12, "changement de toit");
        System.out.println("\n  ---------------- Add objet planning (Pl) ----------------\n");
        Pl.afficherListMaint();
        Pl.modifierMaint(new Date(124, 07, 30), 96, "changement de moteur");
        System.out.println("\n  ---------------- apres modifier planning (Pl) date= 2024 ----------------\n\n");
        Pl.afficherListMaint();

        // maintenance -----------------------------------------------------------------
        System.out.println("\n\t---------------- Maintenance ----------------\n");
        Maintenance m1 = Pl.getMainList().get(0);
        m1.ajouterPiece(1, "toit", 1, 270);
        m1.ajouterPiece(p2);
        m1.afficherListPieces();

        System.out.println("\n---------------- apres modifierPiece ref = 1----------------\n");
        m1.modifierPiece(1, "capeau", 2, 300);
        m1.afficherListPieces();




         // Reparation -----------------------------------------------------------------
        System.out.println("\n\t---------------- Reparation ----------------\n");
        Reparation r1 = new Reparation(new Date(124, 07, 30), 12, "voiteur");
        r1.ajouterPiece(p1);
        r1.ajouterPiece(2, "moteur", 1, 500);
        r1.ajouterPiece(3, "port de voiteur", 2, 250);
        r1.afficherListPieces();

        System.out.println("\n---------------- apres supprimerPiece ref = 2----------------\n");
        r1.supprimerPiece(2);
        r1.afficherListPieces();

        System.out.println("\n------------- apres modifierPiece ref = 3-------------\n");
        r1.modifierPiece(3, "caro de voiture", 2, 300);
        r1.afficherListPieces();
    }

}

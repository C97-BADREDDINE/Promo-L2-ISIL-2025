

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Reparation {

    private Date dateRep;
    private int nbHeure;
    private String travaux;
    private List<Piece> listePieceRep;
    private Equipement equipement;
    private Technicien techinicien;

    public Reparation(Date dateRep, int nbHeure, String travaux) {
        this.dateRep = dateRep;
        this.nbHeure = nbHeure;
        this.travaux = travaux;
        listePieceRep = new ArrayList<>();
    }

    public Date getDateRep() {
        return dateRep;
    }

    public void setDateRep(Date dateRep) {
        this.dateRep = dateRep;
    }

    public int getNbHeure() {
        return nbHeure;
    }

    public void setNbHeure(int nbHeure) {
        this.nbHeure = nbHeure;
    }

    public String getTravaux() {
        return travaux;
    }

    public void setTravaux(String travaux) {
        this.travaux = travaux;
    }

    public List<Piece> getPieces() {
        return listePieceRep;
    }

    public  boolean isreparation() {
        return dateRep != null && nbHeure > 0;
    }

    public void ajouterPiece(Piece p) {
        listePieceRep.add(p);
    }

    public void modifierPiece(int ref, String nom, int quntite, double prix) {
        for (Piece piece : listePieceRep) {
            if (piece.getRef() == ref) {
                piece.setNom(nom);
                piece.setQuantite(quntite);
                piece.setPrix(prix);
            }
        }
    }

    public void supprimerPiece(int ref) {
        for (Piece piece : listePieceRep) {
            if (piece.getRef() == ref) {
                listePieceRep.remove(piece);
            }
        }
    }

    @Override
    public String toString() {
        return "Reparation \n" + "\t dateRep=" + dateRep + "\t nbHeure=" + nbHeure + "\t travaux=" + travaux
                + "\t pieces=" + listePieceRep + '\n';
    }

    public void afficherListPieces() {
        System.out.println(toString());
    }

}

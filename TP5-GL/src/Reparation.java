

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

    public  boolean estReformable(Piece p) {
        for (Piece piece : listePieceRep) {
            if (piece.getRef() == p.getRef()) {
                return true;
            }
        }
        return false;
    }

    public double calculerMontantRéparation() {	      
        double montant = 0;
        for (Piece piece : listePieceRep) {
            montant += piece.getPrix();
        }
        montant += nbHeure * 10;
        return montant;
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

    public void affecterEquipement(Equipement e) {
        this.equipement = e;
    }

    public void affecterTechnicien(Technicien t) {
        this.techinicien = t;
    }

    public void afficherEquipement() {
        System.out.println(equipement);
    }

    public void afficherTechnicien() {
        System.out.println(techinicien);
    }
}

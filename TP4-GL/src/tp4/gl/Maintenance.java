package tp4.gl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Maintenance {

    private Date dateMain;
    private int nbheure;
    private String travaux;
    private List<Piece> listePieceRep;

    public Maintenance() {
        listePieceRep = new ArrayList<>();
    }

    public Maintenance(Date dateMain, int nbheure, String travaux) {
        this.dateMain = dateMain;
        this.nbheure = nbheure;
        this.travaux = travaux;
        listePieceRep = new ArrayList<>();
    }

    public Date getDateMain() {
        return dateMain;
    }

    public void setDateMain(Date dateMain) {
        this.dateMain = dateMain;
    }

    public int getNbheure() {
        return nbheure;
    }

    public void setNbheure(int nbheure) {
        this.nbheure = nbheure;
    }

    public String getTravaux() {
        return travaux;
    }

    public void setTravaux(String travaux) {
        this.travaux = travaux;
    }
    
    public List<Piece> getPieceMainList() {
        return listePieceRep;
    }

    public void setPieceMainList(List<Piece> pieceMainList) {
        this.listePieceRep = pieceMainList;
    }
    

    
    public void ajouterPiece(int ref, String nom, int quntite, double prix) {
        listePieceRep.add(new Piece(ref, nom, quntite, quntite, prix));
    }

    public void ajouterPiece(Piece p){
        listePieceRep.add(p);
    }
    
    public void modifierPiece(int ref, String nom, int quntite, double prix){
        for (Piece piece : listePieceRep) {
            if(piece.getRef() == ref){
                piece.setNom(nom);
                piece.setQuantite(quntite);
                piece.setPrix(prix);
            }
        }
    }
    
    public void supprimerPiece(int ref){
        for (Piece piece : listePieceRep) {
            if(piece.getRef() == ref){
                listePieceRep.remove(piece);
            }
        }
    }
    
    
    @Override
    public String toString() {
        return "Maintenance \n dateMain=" + getDateMain() + "\t nbheure= " + getNbheure() + "\t travaux= " + getTravaux() + "\n";
    }

    public void afficherListPieces(){
        for (Piece piece : listePieceRep) {
            System.out.println(piece.toString());
        }
    }


}

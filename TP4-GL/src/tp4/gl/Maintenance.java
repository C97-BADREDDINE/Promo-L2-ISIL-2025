package tp4.gl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Maintenance {

    private Date dateMain;
    private int nbheure;
    private String travaux;
    private List<Piece> pieces;

    public Maintenance() {
        pieces = new ArrayList<>();
    }

    public Maintenance(Date dateMain, int nbheure, String travaux) {
        this.dateMain = dateMain;
        this.nbheure = nbheure;
        this.travaux = travaux;
        pieces = new ArrayList<>();
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
        return pieces;
    }

    public void setPieceMainList(List<Piece> pieceMainList) {
        this.pieces = pieceMainList;
    }
    

    
    public void ajouterPiece(int ref, String nom, int quntite, double prix) {
        pieces.add(new Piece(ref, nom, quntite, quntite, prix));
    }

    public void ajouterPiece(Piece p){
        pieces.add(p);
    }
    
    public void modifierPiece(int ref, String nom, int quntite, double prix){
        for (Piece piece : pieces) {
            if(piece.getRef() == ref){
                piece.setNom(nom);
                piece.setQuantite(quntite);
                piece.setPrix(prix);
            }
        }
    }
    
    public void supprimerPiece(int ref){
        for (Piece piece : pieces) {
            if(piece.getRef() == ref){
                pieces.remove(piece);
            }
        }
    }
    
    
    @Override
    public String toString() {
        return "Maintenance [dateMain=" + getDateMain() + ", nbheure=" + getNbheure() + ", travaux=" + getTravaux() + "]\n";
    }

    public void afficherListPieces(){
        for (Piece piece : pieces) {
            System.out.println(piece.toString());
        }
    }


}

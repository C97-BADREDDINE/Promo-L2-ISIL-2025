package tp4.gl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reparation {

    private Date dateRep;
    private int nbHeure;
    private String travaux;
    private List<Piece> pieces;
    

    public Reparation() {
        pieces = new ArrayList<>();
    }

    public Reparation(Date dateRep, int nbHeure, String travaux, List<Piece> pieces) {
        this.dateRep = dateRep;
        this.nbHeure = nbHeure;
        this.travaux = travaux;
        this.pieces = pieces;
        pieces = new ArrayList<>();
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
        return pieces;
    }

    public double calculerPrixRep(Equipement e) {
        double prix = 0;
        for (Piece piece : pieces) {
            prix += e.getPrice() * nbHeure+piece.getQuantite()*2;
        }
        return prix;
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
        return "Reparation{" + "dateRep=" + dateRep + ", nbHeure=" + nbHeure + ", travaux=" + travaux + ", pieces=" + pieces + '}';
    }

    public void afficherMontantReparation(){
        System.out.println("Le montant de la reparation est : " + calculerPrixRep(new Equipement()));
    }

    public void afficherListPieces(){
        for (Piece piece : pieces) {
            System.out.println(piece.toString()+toString());
        }
    }

}

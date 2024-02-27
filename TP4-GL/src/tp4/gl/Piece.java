package tp4.gl;

public class Piece {
    //attribut de Piece

    private int ref;
    private String nom;
    private int quntite;
    private int qantiteStock;
    private double prix;

    //methode de Piece
    public boolean isDispo(String nom) {
        if(this.qantiteStock == 0){
            return false ;
        }else{
            return true;
        }
    }

    public void entrerStock(int quantite) {
        this.qantiteStock += quantite;
    }

    public double calculerMontantPièce() {
        return prix*quntite;
    }
}

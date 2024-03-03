package tp4.gl;


public class Piece {

  // attributes
  private int ref;
  private String nom;
  private int quantite;
  private static int QuantiteStock;
  private double prix;

  // constructor
  public Piece(int ref, String nom, int quantite, int quantiteStock, double prix) {
    this.ref = ref;
    this.nom = nom;
    this.quantite = quantite;
    Piece.QuantiteStock = quantiteStock;
    this.prix = prix;
  }

  // methods

  // Check if a piece with a specific name is available
  public boolean isDispo(String nom) {
    return this.nom.equals(nom) && quantite > 0;
  }

  // Update stock quantity
  public void entrerStock(int quantite) {
    if (quantite > 0) {
      Piece.QuantiteStock += quantite;
    } else {
      System.out.println("Invalid quantity. Please enter a positive value.");
    }
  }

  // Calculate the total price of a piece
  public double calculerMontantPiece() {
    return quantite * prix;
  }

    //getteur and setteur
    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getQuantiteStock() {
        return QuantiteStock;
    }

    public void setQuantiteStock(int quantiteStock) {
      Piece.QuantiteStock = quantiteStock;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Piece \n ref= " + ref + "\t nom: " + nom + "\t quantite: " + quantite + "\t quantiteStock=" + Piece.QuantiteStock
                + "\t prix=" + prix + "";
    }

}

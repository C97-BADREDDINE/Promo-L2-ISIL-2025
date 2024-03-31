

public class Produit {
    private int codeBarre;
    private String designation;
    private int qauntite;
    private double prix;
    private Categorie categorie;

    public Produit() {
        this.codeBarre = 0;
        this.designation = "";
        this.qauntite = 0;
        this.prix = 0;
    }

    public Produit(int codeBarre, String designation, int qauntite, double prix, Categorie categorie) {
        this.codeBarre = codeBarre;
        this.designation = designation;
        this.qauntite = qauntite;
        this.prix = prix;
        this.categorie = categorie;
    }

    public int getCodeBarre() {
        return codeBarre;
    }

    public String getDesignation() {
        return designation;
    }

    public int getQuantite() {
        return qauntite;
    }

    public double getPrix() {
        return prix;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCodeBarre(int codeBarre) {
        this.codeBarre = codeBarre;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setQuantite(int qauntite) {
        this.qauntite = qauntite;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }


    
    @Override
    public String toString() {
        return "Produit [codeBarre=" + codeBarre + ", designation=" + designation + ", qauntite=" + qauntite + ", prix="
                + prix + ", categorie=" + categorie + "]";
    }

    public void afficher() {
        System.out.println("Code barre: " + codeBarre);
        System.out.println("Designation: " + designation);
        System.out.println("Quantite: " + qauntite);
        System.out.println("Prix: " + prix);
        System.out.println("Categorie: " + categorie);
    }


}

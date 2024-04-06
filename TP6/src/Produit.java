

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
        return "Produit :"+
        "\n\tCode barre= " + codeBarre + "\n" +
        "\tdesignation= " + designation + "\n" +
        "\tqauntite= " + qauntite + "\n" +
        "\tprix= " + prix + "\n" +
        "\tcategorie=" + categorie + "\n";
    }

    public void afficher() {
        System.out.println(toString());
    }


}

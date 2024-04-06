public class LigneFacture {
    private int numLigne;
    private int quantiteLigne;
    private double mountantLigne;
    private Produit produit;

    public LigneFacture() {
        this.numLigne = 0;
        this.quantiteLigne = 0;
        this.mountantLigne = 0;
    }

    public LigneFacture(int numLigne, int quantiteLigne, double mountantLigne) {
        this.numLigne = numLigne;
        this.quantiteLigne = quantiteLigne;
        this.mountantLigne = mountantLigne;
    }

    public int getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(int numLigne) {
        this.numLigne = numLigne;
    }

    public int getQuantiteLigne() {
        return quantiteLigne;
    }

    public void setQuantiteLigne(int quantiteLigne) {
        this.quantiteLigne = quantiteLigne;
    }

    public double getMountantLigne() {
        return mountantLigne;
    }

    public void setMountantLigne(double mountantLigne) {
        this.mountantLigne = mountantLigne;
    }

    @Override
    public String toString() {
        return "LigneFactures [numLigne=" + numLigne + ", quantiteLigne=" + quantiteLigne + ", mountantLigne="
                + mountantLigne + "]";
    }

    public void afficher() {
        System.out.println("-Numero de ligne facutre: " + numLigne);
        System.out.println("  Quantite de ligne facture : " + quantiteLigne);
        System.out.println("  Montant de ligne facture: " + calculerMontantLigne()+" DA");
    }

    public String getDesignation() {
        return produit.getDesignation();
    }

    public double calculerMontantLigne() {
        this.mountantLigne= quantiteLigne * produit.getPrix();
        return mountantLigne;
    }

    public void setProduit(Produit produit) {
        if(produit != null)
            this.produit = produit;
    }

    public Produit getProduit() {
        return produit;
    }
}

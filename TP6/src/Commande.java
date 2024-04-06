import java.time.LocalDate;

public class Commande {
    private LocalDate DateCde;
    private int quantiteCde;
    private Client client;
    private Produit produit;
    
    public Commande(LocalDate DateCde ,int quantiteCde){
        this.DateCde=DateCde;
        this.quantiteCde=quantiteCde;
    }

    public LocalDate getDateCde() {
        return DateCde;
    }

    public void setDateCde(LocalDate DateCde) {
        this.DateCde = DateCde;
    }

    public int getQuantiteCde() {
        return quantiteCde;
    }

    public void setQuantiteCde(int quantiteCde) {
        this.quantiteCde = quantiteCde;
    }
    
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    
}

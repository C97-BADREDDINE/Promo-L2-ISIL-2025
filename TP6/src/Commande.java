import java.time.LocalTime;

public class Commande {
    private LocalTime DateCde;
    private int quantiteCde;
    private Client client;
    private Produit produit;
    
    public Commande(LocalTime DateCde ,int quantiteCde){
        this.DateCde=DateCde;
        this.quantiteCde=quantiteCde;
    }

    public LocalTime getDateCde() {
        return DateCde;
    }

    public void setDateCde(LocalTime DateCde) {
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

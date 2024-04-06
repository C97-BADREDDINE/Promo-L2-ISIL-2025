
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Facture {
    private int numFacture;
    private LocalDate dateFacture;
    private double montant = 0;
    private Client client;
    private ArrayList<LigneFacture> lignesFacture = new ArrayList<LigneFacture>();

    public Facture() {
        this.numFacture = 0;
        this.dateFacture = LocalDate.now();
    }

    public int getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(int numFacture) {
        this.numFacture = numFacture;
    }

    public LocalDate getDateFacture() {
        return dateFacture;
    }

    public Client getClient() {
        return client;
    }

    public void setDateFacture(LocalDate dateFacture) {
        this.dateFacture = dateFacture;
    }

    public double getMontant() {
        return montant;
    }

    public ArrayList<LigneFacture> getLignesFacture() {
        return lignesFacture;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double calculerMontant() {
        if (lignesFacture.isEmpty()) {
            return 0;
        }

        
        for (LigneFacture ligneFacture : lignesFacture) {
            montant += ligneFacture.calculerMontantLigne();
        }
        return montant;
    }

    public void ajouterLigneFacture(LigneFacture ligneFacture) {
        lignesFacture.add(ligneFacture);
    }

    public void afficher() {
        System.out.println("Numero de facture: " + getNumFacture());
        System.out.println("Date de facture: " + getDateFacture());
        System.out.println("Client: " + client.getNom());
        for (LigneFacture ligneFacture : lignesFacture) {
            ligneFacture.afficher();
        }
        System.out.println("\t\t-------------------------------");
        System.out.println("\t\t|Montant de facture: " +getMontant() +" DA|");
        System.out.println("\t\t-------------------------------");
    }

    public abstract void etablirFacture(Client client, int numFacture, LocalDate dateFacture);

    public String getNomClient() {
        return client.getNom();
    }

    public void setNomClient(String nom) {
        client.setNom(nom);
    }

    public String getPrenomClient() {
        return client.getPrenom();
    }

    public void setPrenomClient(String prenom) {
        client.setPrenom(prenom);
    }

}
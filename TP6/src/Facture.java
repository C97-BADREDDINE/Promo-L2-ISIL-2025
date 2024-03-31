
import java.time.LocalTime;
import java.util.ArrayList;

public abstract class Facture {
    private int numFacture;
    private LocalTime dateFacture;
    private double montant = 0;
    private Client client;
    private ArrayList<LigneFacture> lignesFacture = new ArrayList<LigneFacture>();

    public Facture() {
        this.numFacture = 0;
        this.dateFacture = LocalTime.now();
    }

    public Facture(int numFacture, LocalTime dateFacture, double montant) {
        this.numFacture = numFacture;
        this.dateFacture = dateFacture;
        this.montant = montant;
    }

    public int getNumFacture() {
        return numFacture;
    }

    public void setNumFacture(int numFacture) {
        this.numFacture = numFacture;
    }

    public LocalTime getDateFacture() {
        return dateFacture;
    }

    public Client getClient() {
        return client;
    }

    public void setDateFacture(LocalTime dateFacture) {
        this.dateFacture = dateFacture;
    }

    public double getMontant() {
        return montant;
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

        double montant = 0;
        for (LigneFacture ligneFacture : lignesFacture) {
            montant += ligneFacture.calculerMontantLigne();
        }
        return montant;
    }

    public void ajouterLigneFacture(LigneFacture ligneFacture) {
        lignesFacture.add(ligneFacture);
        this.montant += ligneFacture.calculerMontantLigne();
    }

    public void supprimerLigneFacture(int numLigne) {
        for (LigneFacture ligneFacture : lignesFacture) {
            if (ligneFacture.getNumLigne() == numLigne) {
                lignesFacture.remove(ligneFacture);
                this.montant -= ligneFacture.calculerMontantLigne();
            }
        }
    }

    public void afficher() {
        System.out.println("Numero de facture: " + numFacture);
        System.out.println("Date de facture: " + dateFacture);
        System.out.println("Montant de facture: " + montant);
        System.out.println("Client: " + client.getNom());
        for (LigneFacture ligneFacture : lignesFacture) {
            ligneFacture.afficher();
        }
    }

    public abstract void etablirFacture(Client client, int numFacture, LocalTime dateFacture);

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
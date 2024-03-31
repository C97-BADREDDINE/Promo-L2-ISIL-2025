import java.time.LocalTime;

public class Facture_Entreprise extends Facture implements Critere {
    private int TVA;

    public Facture_Entreprise() {
        super();
        this.TVA = 0;
    }

    public Facture_Entreprise(int numFacture, LocalTime dateFacture, double montant, int TVA) {
        super(numFacture, dateFacture, montant);
        this.TVA = TVA;
    }

    public int getTVA() {
        return TVA;
    }

    public void setTVA(int TVA) {
        this.TVA = TVA;
    }

    public void etablirFacture(Client client, int numFacture, LocalTime dateFacture) {
        this.setClient(client);
        this.setNumFacture(numFacture);
        this.setDateFacture(dateFacture);
        this.setMontant(calculerMontant() + TVA);
    }

    public boolean Ok(Object o) {
        try {
            if (o instanceof Facture_Entreprise) {
                Facture_Entreprise facture = (Facture_Entreprise) o;
                return facture.getTVA() < 5000;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

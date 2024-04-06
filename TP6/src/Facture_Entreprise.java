import java.time.LocalDate;

public class Facture_Entreprise extends Facture implements Critere {
    private int TVA;

    public Facture_Entreprise() {
        this.TVA = 0;
    }

    public int getTVA() {
        return TVA;
    }

    public void setTVA(int TVA) {
        this.TVA = TVA;
        
    }

    public void etablirFacture(Client client, int numFacture, LocalDate dateFacture) {
        this.setClient(client);
        this.setNumFacture(numFacture);
        this.setDateFacture(dateFacture);
        TVA=(int) (calculerMontant() * 20 / 100);
        setMontant(calculerMontant()+TVA);
    }

    public void afficher(){
        System.out.println("Montant TVA :"+TVA+"DA");
        super.afficher();
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

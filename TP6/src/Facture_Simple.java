import java.time.LocalTime;

public class Facture_Simple extends Facture{
    private int bonus;

    public Facture_Simple() {
        super();
        this.bonus = 0;
    }

    public Facture_Simple(int numFacture, LocalTime dateFacture, double montant, int bonus) {
        super(numFacture, dateFacture, montant);
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public void etablirFacture(Client client ,int numFacture ,LocalTime dateFacture){
        this.setClient(client);
        this.setNumFacture(numFacture);
        this.setDateFacture(dateFacture);
        this.setMontant(calculerMontant()+bonus);
    }


}

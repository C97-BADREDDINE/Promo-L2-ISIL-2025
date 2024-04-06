import java.time.LocalDate;

public class Facture_Simple extends Facture{
    private int bonus;

    public Facture_Simple() {
        this.bonus = 0;
    }

    public Facture_Simple(int bonus) {
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public void etablirFacture(Client client ,int numFacture ,LocalDate dateFacture){
        this.setClient(client);
        this.setNumFacture(numFacture);
        this.setDateFacture(dateFacture);
        setBonus(5);
        setMontant(calculerMontant()+bonus);
    }

    public void afficher(){
        System.out.println("bonus : "+bonus+" DA");
        super.afficher();
    }


}

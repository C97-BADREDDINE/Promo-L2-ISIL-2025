import java.math.*;
import java.util.Date;

public class Chef_Atelier extends Personne {

    private int nbExperience;
    private Date dateExperience;
    private Date datePromotion;
    private Statut statut;

    public Chef_Atelier(String nom, String prenom, String email, String login, String password, int nbExperience, Date dateExperience, Date datePromotion, Statut statut) {
        super(nom, prenom, email, login, password);
        this.nbExperience = nbExperience;
        this.dateExperience = dateExperience;
        this.datePromotion = datePromotion;
        this.statut = statut;
    }

    public Chef_Atelier() {
       // super();
        this.nbExperience = 0;
        this.dateExperience = new Date(0/0/0);
        this.datePromotion = new Date(0/0/0);
        this.statut = statut.Neant;
    }

    public int getNbExperience() {
        return nbExperience;
    }

    public void setNbExperience(int nbExperience) {
        this.nbExperience = nbExperience;
    }

    public Date getDateExperience() {
        return dateExperience;
    }

    public void setDateExperience(Date dateExperience) {
        this.dateExperience = dateExperience;
    }

    public Date getDatePromotion() {
        return datePromotion;
    }

    public void setDatePromotion(Date datePromotion) {
        this.datePromotion = datePromotion;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public String toString() {
        return super.toString()
                + ", nbExperience=" + getNbExperience()
                + ", dateExperience=" + getDateExperience()
                + ", datePromotion=" + getDatePromotion()
                + ", statut=" + statut;
    }

    public void afficher() {
        System.out.println(toString());
    }

    public int miseAJourExperience(Date dateExp) {
        int DayParMillesSconds = 1000 * 60 * 60 * 24;
        int Def = (int) (Math.abs(dateExperience.getTime() - dateExp.getTime()) / DayParMillesSconds);
        while (Def > 365) {
            nbExperience++;
        }
        return nbExperience;
    }

}

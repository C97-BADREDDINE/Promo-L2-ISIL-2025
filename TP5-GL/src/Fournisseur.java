import java.util.Vector;

public class Fournisseur {
    private int numRC;
    private String raisonSocial;
    private String adresse;
    private double capital;
    private Vector<Equipement> equipements = new Vector<Equipement>();

    public Fournisseur(int numRC, String raisonSocial, String adresse, double capital) {
        this.numRC = numRC;
        this.raisonSocial = raisonSocial;
        this.adresse = adresse;
        this.capital = capital;
    }

    public int getNumRC() {
        return numRC;
    }

    public void setNumRC(int numRC) {
        this.numRC = numRC;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public Vector<Equipement> getEquipements() {
        return equipements;
    }

    public void setEquipements(Vector<Equipement> equipements) {
        this.equipements = equipements;
    }

    public void ajouterEquipement(Equipement e) {
        equipements.add(e);
    }

    public void supprimerEquipement(Equipement e) {
        equipements.remove(e);
    }

}
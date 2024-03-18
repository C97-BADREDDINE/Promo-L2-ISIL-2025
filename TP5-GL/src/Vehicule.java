public class Vehicule {
    private int immatricule;
    private String marque;
    private Technicien conducteur;

    public Vehicule(int immatricule, String marque) {
        this.immatricule = immatricule;
        this.marque = marque;
    }

    public int getImmatricule() {
        return immatricule;
    }

    public void setImmatricule(int immatricule) {
        this.immatricule = immatricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Technicien getConducteur() {
        return conducteur;
    }

    public void setConducteur(Technicien conducteur) {
        this.conducteur = conducteur;
    }

    public void ajouterTechnicien(Technicien t){
        t.ajouetVehicule(this);
    }

    /*@Override
    public String toString() {
        return "Vehicule conducteur=" +conducteur.getNom() + ", immatricule=" + immatricule + ", marque=" + marque + "";
    }*/

    public String toString() {
        return "\tVehicule : marque= " + marque +"\timmatricule= "+ immatricule  ;
    }

    public void AfficherVhicule(){
        System.out.println("\nConducteur "+conducteur.getNom()+"\t"+toString()+"\n");
    }

}

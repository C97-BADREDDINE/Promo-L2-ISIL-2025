import java.util.ArrayList;


public class Technicien extends Personne{

    private int matricule;
    private Specialite specialite;
    private Vehicule engin;

    private Technicien chefEquipe;
    private ArrayList<Technicien> membre;

    public Technicien(int matricule,Vehicule eng, Specialite specialite) {
        this.matricule = matricule;
        this.engin = eng;
        this.specialite = specialite;
        this.membre = new ArrayList<>();
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    public Vehicule getEngin() {
        return engin;
    }

    public void setEngin(Vehicule engin) {
        this.engin = engin;
    }

    public Technicien getChefEquipe() {
        return chefEquipe;
    }

    public void setChefEquipe(Technicien chefEquipe) {
        this.chefEquipe = chefEquipe;
    }

    public ArrayList<Technicien> getMembre() {
        return membre;
    }

    public void setMembre(ArrayList<Technicien> membre) {
        this.membre = membre;
    }



    public void designerChefEquipe(Technicien t) {
        this.chefEquipe = t;
        if(!t.getMembre().contains(this))
            this.membre.add(this);
    }

    public void ajouetVehicule(Vehicule v) {
        if (membre.isEmpty()) {
            System.out.println("No technician available.");
        } else {
            this.engin = v;
            if (membre.contains(this)) {
                System.out.println("Technician is already a member.");
                membre.remove(this);
            }
        }
    }

}

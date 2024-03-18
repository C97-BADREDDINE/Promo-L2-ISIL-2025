import java.util.ArrayList;


public class Technicien extends Personne{

    private int matricule;
    public Specialite specialite;
    private Vehicule engin;

    private Technicien chefEquipe;
    private ArrayList<Technicien> membre;

    public Technicien(String nom,int matricule,Vehicule eng, Specialite specialite) {
        super.setNom(nom);
        this.matricule = matricule;
        this.engin = eng;
        this.specialite = specialite;
        this.membre = new ArrayList<>();
        chefEquipe = this;
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

    public void ajouterMembre(Technicien t) {
        this.membre.add(t);
        t.designerChefEquipe(this);
    }

    public void designerChefEquipe(Technicien t) {
        this.chefEquipe = t;
       /* if(!t.getMembre().contains(this))
            this.membre.add(this);*/
    }

    public void ajouetVehicule(Vehicule v) {
        this.engin = v;
        v.setConducteur(this);
    }

    @Override
    public String toString() {
        return "\nnom= "+ getNom() +"\tmatricule= " + matricule + "\t specialite= " + specialite + "\t chefEquipe= " + chefEquipe.getNom() + "\nengin "  + engin  + ".\n";
    }

    public void AfficherTechnicien(){
        System.out.println("Technicien "+toString());
    }
    
}

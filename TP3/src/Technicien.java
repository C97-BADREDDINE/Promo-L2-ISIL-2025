
public class Technicien extends Personne{
     private int matricule;
     private Specialite specialite;

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
     
     public Technicien(String nom, String prenom, String email, String login, String password, int matricule, Specialite specialite) {
        super(nom, prenom, email, login, password);
        this.matricule = matricule;
        this.specialite = specialite;
    }
     
     public Technicien() {
        //super();
        this.matricule = 0;
        this.specialite = specialite.Rien;
    }
     
     public String toString() {
        return super.toString()
                + ", matricule=" + matricule
                + ", specialite=" + specialite
                + '}';
    }
     
   public void afficher(){
       System.out.println(this.toString());
   }
}

   


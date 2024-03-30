
public class Client {
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String email;

    public Client(String nom, String prenom, String adresse, String tel, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTelephone() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelephone(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client :" +
                "\tnom='" + nom + "'\n" +
                "\t, prenom='" + prenom + "'\n" +
                "\t, adresse='" + adresse + "'\n" +
                "\t, tel='" + tel + "'\n" +
                "\t, email='" + email + "'\n";
    }

    public void afficher() {
        System.out.println(this.toString());
    }
}


public class Client {
    private int numero;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;

    public Client() {
        this.nom = "";
        this.prenom = "";
        this.adresse = "";
        this.tel = "";
        this.numero = 0;
    }

    public Client(int numero, String nom, String prenom, String adresse, String tel) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
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

    public int getNumero() {
        return numero;
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

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Client :" +
                "\n\tnumero=" + numero + "\n" +
                "\tnom='" + nom + "'\n" +
                "\t, prenom='" + prenom + "'\n" +
                "\t, adresse='" + adresse + "'\n" +
                "\t, tel='" + tel + "'\n";
    }

    public void afficher() {
        System.out.println(this.toString());
    }
}

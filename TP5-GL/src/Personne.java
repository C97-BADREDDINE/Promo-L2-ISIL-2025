
import java.util.Scanner;

public class Personne {

    private String nom, prenom, email, login, password;

    public Personne() {
        this.nom = "";
        this.prenom = "";
        this.email = "";
        this.login = "";
        this.password = "";
    }

    public Personne(String nom, String prenom, String email, String login, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "Nom: " + nom + ", Prenom: " + prenom + ", Email: " + email + ", Login : " + login + ", Password : " + password;
    }

    public void afficher() {
        System.out.println(toString());
    }

    public boolean authentifier1() {
        //new login by user
        String Login, Password;
        Scanner input = new Scanner(System.in);
        
        System.out.print("Username :");
        Login = input.next();
        System.out.print("Password :");
        Password = input.next();
        if (this.login.equals(Login) && this.password.equals(password)) {
            System.out.println("Succfully login");
            return true;
        } else {
            System.out.println("Echec! please check your password or username");
            return false;
        }
    }

    public boolean authentifier2() {
        // Implémentez ici la logique d'authentification après 3 tentatives
        int i = 0;
        while (i < 3 && this.authentifier1() == false) {
            i++;
        }
        if (i == 3) {
            return false;
        }
        return true;
    }

}

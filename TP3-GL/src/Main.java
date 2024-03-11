
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws ParseException {
        Technicien tec1, tec2, tec3;
        tec1 = new Technicien("Bensalma", "Badreddine", "Badreddine.Bensalma@gmail.com", "C97_Padri", "12345DFFG", 31389211, Specialite.Informatique);
        tec2 = new Technicien("B", "Bad", "bakayombo@gmail.com", "Padri", "12DFtG", 31389216, Specialite.Informatique);
        tec3 = new Technicien();

        // tec1.afficher();
        // tec2.afficher();
        // tec3.afficher();
        // System.out.println(tec1.authentifier2());
        //---------------------------------------------------------------------------------------------------------------
        SimpleDateFormat Tdate = new SimpleDateFormat("dd/MM/yyyy");
        Chef_Atelier chef1 = new Chef_Atelier("Basd", "jalil", "abcd@gmail.com", "basd", "1234basd", 3, Tdate.parse("11/01/2014"), Tdate.parse("11/01/2024"), Statut.Suspendu);
        Chef_Atelier chef2 = new Chef_Atelier("batoul", "meriem", "batoulmeriem@usthb.com", "XX_Meriem_XX", "meriem654!", 2, Tdate.parse("15/09/2018"), Tdate.parse("26/06/2016"), Statut.Actif);
      //  Chef_Atelier chef3 = new Chef_Atelier();

        chef1.afficher();
        chef2.afficher();
       // chef3.afficher();

    }
}

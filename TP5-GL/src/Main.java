import java.util.Date;

public class Main {

    public static void main(String[] args) {
       /* Date d1 = new Date(121, 9, 10);
        Date d2 = new Date(121, 9, 11);
        Atelier a1 = new Atelier(1, d1, d2);
        System.out.println(a1);

     Personne personne1 = new Personne("John", "Doe", "1234@ad.sda", "login", "1234");
    Personne personne2 = new Personne("Jane", "Smith", "ada@d", "login", "1234");
    Personne personne3 = new Personne("Alice", "Johnson",  "adpi@d", "login", "1234");*/

        /*Equipement e1 = new Equipement(1, "MICRO", 1000, TypeEquipment.MICRO, a1);
        Equipement e2 = new Equipement(2, "Data show", 2000, TypeEquipment.DATASHOW, a1);

        Fournisseur f1 = new Fournisseur(1, "AB", "alger", 40000);
        Fournisseur f2 = new Fournisseur(2, "CD", "oran", 50000);

        f1.ajouterEquipement(e1);
        f1.ajouterEquipement(e2);
        System.out.println(f1);

        e1.setFournisseur(f2);
        System.out.println("apres chager fourniteur"+e1);

        Piece p1 = new Piece(1, "Dell inspiron", 10, 10, 80000);
        Piece p2 = new Piece(2, "Acer Nitro 5", 20, 20, 90000);

        Reparation r1 = new Reparation(new Date(124, 3, 16), 96, "reparation Ecran");
        // ajouter piece de reparation Ecran de Dell inspiron
        r1.ajouterPiece(p1);
        r1.ajouterPiece(p2);
        System.out.println(r1.isreparation());*/

        Vehicule v1 = new Vehicule(123154, "Peugeot");
        Vehicule v2 = new Vehicule(245454, "Renault");

        Technicien T1 = new Technicien("Yazid",222, v1, Specialite.MECANIQUE);
        Technicien T2 = new Technicien("Ahmed", 333, v1, Specialite.ELECTRONIQUE);
        Technicien T3 = new Technicien("Ali", 444, v1, Specialite.MECANIQUE);
        Technicien T4 = new Technicien("Omar", 555, v1, Specialite.ELECTRONIQUE);

        //Omar est chef de Technicien de Yazid 
        T1.designerChefEquipe(T4);
        T1.ajouetVehicule(v1);
        System.out.println(T1);

        //add condicture Ahmed de Vehicule <<V2>> 
        v2.ajouterTechnicien(T2);
        //afficher Vehicule <<V2>> avec condicture Ahmed
        v2.AfficherVhicule();

        
        //afficher Technicien <<T2>> avec Vehicule <<V2>>
        System.out.println(T2);

    }

}

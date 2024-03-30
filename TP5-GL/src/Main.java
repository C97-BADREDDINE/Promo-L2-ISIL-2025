import java.util.Date;

public class Main {

    public static void main(String[] args) {

        //------------------------relation Equipement avec Atelier et Fournisseur -------------------------------------
        System.out.println("\n --------relation Equipement avec Atelier et Fournisseur ---------------------\n");
        Atelier a1 = new Atelier(1, new Date(121, 9, 10), new Date(121, 9, 11));
        Atelier a2 = new Atelier(2, new Date(121, 9, 12), new Date(121, 9, 13));
        System.out.println(a1);

        Equipement e1 = new Equipement(1, "MICRO", 1000, TypeEquipment.MICRO, a1);
        Equipement e2 = new Equipement(2, "Data show", 2000, TypeEquipment.DATASHOW, a1);

        //change Atelier de Equipment e2
        e2.setAffecté(a2);
        System.out.println("\nApres changer Atelier"+e2);

        Fournisseur f1 = new Fournisseur(1, "AB", "alger", 4000);
        Fournisseur f2 = new Fournisseur(2, "CD", "oran", 5000);

        f1.ajouterEquipement(e1);
        f1.ajouterEquipement(e2);
        System.out.println(f1);

        //changer fournisseur f1-->f2 de Equipmente (e1)
        e1.setFournisseur(f2);
        System.out.println("\nApres chager fournisseur"+e1);

        //supprimer fournisseur f2 de Equipmente (e2)
        e2.supprimerFournisseur(f1);
        System.out.println("\nApres supprimer fournisseur = "+e2.getFournisseur());






        //------------------------relation techinicien avec vehicule -------------------------------------
        System.out.println("\n --------relation techinicien avec vehicule ---------------------\n");
        Vehicule v1 = new Vehicule(123154, "Peugeot");
        Vehicule v2 = new Vehicule(245454, "Renault");

        Technicien T1 = new Technicien("Yazid",222, v1, Specialite.MECANIQUE);
        Technicien T2 = new Technicien("Ahmed", 333, v1, Specialite.ELECTRONIQUE);
        Technicien T3 = new Technicien("Omar", 555, v1, Specialite.ELECTRONIQUE);

        //Omar est chef de Technicien de Yazid 
        T1.designerChefEquipe(T3);
        T1.ajouetVehicule(v1);
        System.out.println(T1);

        //add condicture Ahmed de Vehicule <<V2>> 
        v2.ajouterTechnicien(T2);
        //afficher Vehicule <<V2>> avec condicture Ahmed
        v2.AfficherVhicule();

        //afficher Technicien <<T2>> avec Vehicule <<V2>>
        System.out.println(T2);
        


        //-----------------------------------relation reparation avec piece---------------------------------- 
        System.out.println("\n --------relation reparation avec piece ---------------------\n");
        Piece p1 = new Piece(1, "Dell inspiron", 10, 10, 800);
        Piece p2 = new Piece(2, "Acer Nitro 5", 20, 20, 900);

        Reparation r1 = new Reparation(new Date(124, 3, 16), 96, "reparation Ecran");
        // ajouter piece de reparation Ecran de Dell inspiron
        r1.ajouterPiece(p1);
        r1.ajouterPiece(p2);
        r1.afficherListPieces();
        System.out.println("p1 est Reformable ? :"+r1.estReformable(p1));
        
        //calculer prix de reparation
        System.out.println("\n\tprix total de reparation = "+r1.calculerMontantRéparation());


    }

}

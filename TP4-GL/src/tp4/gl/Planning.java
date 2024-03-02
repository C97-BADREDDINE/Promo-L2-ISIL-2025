package tp4.gl;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Planning {

    private int numP = 0;
    private List<Maintenance> maintenance;
        
    

    public Planning() {
        maintenance = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez le numero de planning : ");
        numP = sc.nextInt();
    }

    public Planning(int numP) {
        this.numP = numP;
        maintenance = new ArrayList<>();
    }

    public void ajouterMaint(Date dateMaint, int nbHeure, String travaux) {
        maintenance.add( new Maintenance(dateMaint,nbHeure,travaux));
    }
    
    public void modifierMaint(Date dateMaint,int nbHeure, String travaux){
        for (Maintenance maint : maintenance) {
            if(maint.getDateMain().equals(dateMaint)){
                Scanner sc = new Scanner(System.in);
                System.out.println("Entrez le nouveau nombre d'heure : ");
                nbHeure = sc.nextInt();
                System.out.println("Entrez le nouveau travaux : ");
                travaux = sc.next();
                maint.setNbheure(nbHeure);
                maint.setTravaux(travaux);
                
            }
        }
    }

    public int getNumP() {
        return numP;
    }
    
    public void supprimerMaint(Date dateMaint){
        for (Maintenance maint : maintenance) {
            if(maint.getDateMain().equals(dateMaint)){
                maintenance.remove(maint);
            }
        }        
    }

    @Override
    public String toString() {
        return "Planning{" + "numP=" + getNumP() + " " + maintenance + '}'+ "\n";
    }
    
    public void afficherListMaint(){
        System.out.println(toString());
    }

}

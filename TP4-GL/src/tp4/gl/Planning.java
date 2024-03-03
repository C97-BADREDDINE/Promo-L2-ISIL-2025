package tp4.gl;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Planning {

    private int numP = 0;
    private List<Maintenance> listeMaintenance;
        
    

    public Planning() {
        listeMaintenance = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez le numero de planning : ");
        setNumP(sc.nextInt());
        sc.close();        
    }

    public Planning(int numP) {
        this.numP = numP;
        listeMaintenance = new ArrayList<>();
    }

    public void ajouterMaint(Date dateMaint, int nbHeure, String travaux) {
        listeMaintenance.add( new Maintenance(dateMaint,nbHeure,travaux));
    }
    
    public void modifierMaint(Date dateMaint,int nbHeure, String travaux){
        for (Maintenance maint : listeMaintenance) {
           if(maint.getDateMain().equals(dateMaint)){
            maint.setNbheure(nbHeure);
            maint.setTravaux(travaux);
           }
        }
    }

    public int getNumP() {
        return numP;
    }

    public void setNumP(int numP){
        this.numP=numP;
    }

    public List<Maintenance> getMainList() {
        return listeMaintenance;
    }
    
    public void supprimerMaint(Date dateMaint){
        for (Maintenance maint : listeMaintenance) {
            if(maint.getDateMain().equals(dateMaint)){
                listeMaintenance.remove(maint);
                break;
            }
        }        
    }

    @Override
    public String toString() {
        return "Planning:\n" + "\t numP =" + getNumP() + "\t" + listeMaintenance + "\n";
    }
    
    public void afficherListMaint(){
        System.out.println(toString());
    }

}

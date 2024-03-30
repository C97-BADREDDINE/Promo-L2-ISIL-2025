

public class Equipement {
        private int numSérie; 
        private String designation; 
        private double prix;
        private Atelier affecté; 
        private Fournisseur fournisseur;
        public TypeEquipment type;

        public Equipement(int numSérie, String designation, double prix, TypeEquipment type, Atelier affecte) {
            this.numSérie = numSérie;
            this.designation = designation;
            this.prix = prix;
            this.type = type;
            this.affecté = affecte;
        }

     
        public int getNumSérie() {
            return numSérie;
        }

        public void setNumSérie(int numSérie) {
            this.numSérie = numSérie;
        }

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public double getPrix() {
            return prix;
        }

        public void setPrix(double prix) {
            this.prix = prix;
        }

        public Atelier getAffecté() {
            return affecté;
        }

        public void setAffecté(Atelier affecté) {
            this.affecté = affecté;
        }

        public Fournisseur getFournisseur() {
            return fournisseur;
        }

        public void setFournisseur(Fournisseur fournisseur) {
            this.fournisseur = fournisseur;
        }

        public TypeEquipment getTypeEquipement() {
            return type;
        }

        public void setTypeEquipement(TypeEquipment type) {
            this.type = type;
        }

        public void ajouterFournisseur(Fournisseur f) {
            setFournisseur(f);
             if (!f.getEquipements().contains(this)) {
                f.ajouterEquipement(this);
            }
            
        }

        public void supprimerFournisseur(Fournisseur f) {
            if (getFournisseur()==f) {
                this.fournisseur = null;
                System.out.println("Fournisseur supprimé");
            }else{
                System.out.println("Echec de suppression de fournisseur");
            }   
        }


        @Override
        public String toString() {
            return "Equipement [numSérie=" + numSérie + ", designation=" + designation + ", prix=" + prix + ", affecté="
                    + affecté +  ", type=" + type + "]";
        }

        

}

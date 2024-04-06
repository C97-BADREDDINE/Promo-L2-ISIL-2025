import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Création d'un centre commercial
        CentreCommercial centreCommercial = new CentreCommercial("Mon Centre Commercial", "123 Rue Principale");

        // Création et ajout de clients
        Client client1 = new Client(1, "Jean", "Dupont", "1 Rue A", "123456789");
        centreCommercial.ajouterClient(client1);
        Client client2 = new Client(2, "Marie", "Durand", "2 Rue B", "987654321");
        centreCommercial.ajouterClient(client2);

        // Affichage des clients
        System.out.println("Liste des clients:");
        centreCommercial.afficher(centreCommercial.getClients());;

        // Création et ajout de produits
        centreCommercial.ajouterProduit(new Produit(101, "Produit 1", 400, 200, Categorie.Alimentaire));
        centreCommercial.ajouterProduit(new Produit(102, "Produit 2", 350, 415, Categorie.Vestimentaire));

        // Affichage des produits
        System.out.println("Liste des produits:");
        centreCommercial.afficher(centreCommercial.getProduits());

        // Création et ajout de commandes
        // Commande du client 1
        centreCommercial.ajouterCommande(new Commande(LocalDate.of(2024, 04, 2), 3), client1, centreCommercial.chercherProduit(101));
        centreCommercial.ajouterCommande(new Commande(LocalDate.of(2024, 04, 3), 6), client1, centreCommercial.chercherProduit(102));

        // Commande du client 2
        centreCommercial.ajouterCommande(new Commande(LocalDate.of(2024, 03, 28), 50), client2, centreCommercial.chercherProduit(102));
        centreCommercial.ajouterCommande(new Commande(LocalDate.of(2024, 02, 29), 50), client2, centreCommercial.chercherProduit(101));

        // Echec de la commande du client 2 (quantité insuffisante)
        centreCommercial.ajouterCommande(new Commande(LocalDate.of(2024, 01, 1), 1000), client2, centreCommercial.chercherProduit(101));

        // Affichage du client le plus fidèle
        System.out.println("\nClient le plus fidèle:");
        Client clientFidele = centreCommercial.clientFedele();
        if (clientFidele != null) {
            System.out.println(clientFidele);
        } else {
            System.out.println("Aucun client trouvé.");
        }

        // Affichage du produit le plus vendu
        System.out.println("\n\nProduit le plus vendu:");
        Produit produitPlusVendu = centreCommercial.produitPlusVendu();
        if (produitPlusVendu != null) {
            System.out.println(produitPlusVendu);
        } else {
            System.out.println("Aucun produit trouvé.");
        }

        // Création et ajout de factures
        // Facture_Simple spécialment de Client 1
        System.out.println("\n\t\tFacture Simple de Client 1");
        LigneFacture ligneFacture;
        Facture_Simple factureSimple = new Facture_Simple(5);
        
        factureSimple.ajouterLigneFacture(new LigneFacture(1, 3, 200));
        // On ajoute le produit de la ligne de facture pour produit 101 de client 1
        ligneFacture = factureSimple.getLignesFacture().get(0);
        ligneFacture.setProduit(centreCommercial.chercherProduit(101));

        // On ajoute une nouvelle ligne de facture  pour le produit 102 de client 1
        factureSimple.ajouterLigneFacture(new LigneFacture(2, 6, 415));
        ligneFacture = factureSimple.getLignesFacture().get(1);
        ligneFacture.setProduit(centreCommercial.chercherProduit(102));

        // Etablir la facture simple pour le client 1
        factureSimple.etablirFacture(client1, 1, LocalDate.of(2024, 04, 4));
        factureSimple.afficher();
        if(centreCommercial.Ok(centreCommercial.getClients())){
            System.out.println("le nombre de client est supérieur à 100");
        } else {
            System.out.println("le nombre de client est inférieur à 100 ");
        }

        System.out.println("\n\n\t\tFacture Entreprise de Client 2");

        // Facture entreprise spécialment de Client 2
        Facture_Entreprise factureEntreprise = new Facture_Entreprise();
        factureEntreprise.ajouterLigneFacture(new LigneFacture());
        factureEntreprise.ajouterLigneFacture(new LigneFacture(1, 50, 415));

        // On ajoute le produit de la ligne de facture pour produit 102 de client 2
        ligneFacture = factureEntreprise.getLignesFacture().get(0);
        ligneFacture.setProduit(centreCommercial.chercherProduit(102));
        ligneFacture.setNumLigne(0);
        ligneFacture.setQuantiteLigne(50);

        // On ajoute une nouvelle ligne de facture pour le produit 101 de client 2
        ligneFacture = factureEntreprise.getLignesFacture().get(1);
        ligneFacture.setProduit(centreCommercial.chercherProduit(101));
        // Etablir la facture entreprise pour le client 2
        factureEntreprise.etablirFacture(client2, 2, LocalDate.now());
       
        factureEntreprise.afficher();
        if(factureEntreprise.Ok(factureEntreprise)){
            System.out.println("montantTVA est inférieur 5000 DA");
        } else {
            System.out.println("montantTVA est supérieur 5000 DA");
        }	
    }
    
}

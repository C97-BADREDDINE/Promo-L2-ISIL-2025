import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CentreCommercial {

    private String nom;
    private String adresse;
    private ArrayList<Client> clients;
    private ArrayList<Produit> produits;
    private ArrayList<Commande> commandes;

    public CentreCommercial() {
        this.nom = "";
        this.adresse = "";
        clients = new ArrayList<Client>();
        produits = new ArrayList<Produit>();
    }

    public CentreCommercial(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
        clients = new ArrayList<Client>();
        produits = new ArrayList<Produit>();
    }

    public Client chercherClient(int numero) {
        for (Client client : clients) {
            if (client.getNumero() == numero) {
                return client;
            }
        }
        return null;
    }

    public void ajouterClient(Client client) {
        if(chercherClient(client.getNumero()) == null) {
            clients.add(client);

        }else {
            System.out.println("Client déjà existant");
        }
    }

    public void supprimerClient(int numero) {
        if (chercherClient(numero) != null) {
            clients.remove(chercherClient(numero));
        } else {
            System.out.println("Client inexistant");
        }
    }

    public void modifierClient(int numero, String nom, String prenom, String adresse, String telephone) {
        if (chercherClient(numero) != null) {
            chercherClient(numero).setNom(nom);
            chercherClient(numero).setPrenom(prenom);
            chercherClient(numero).setAdresse(adresse);
            chercherClient(numero).setTelephone(telephone);
        } else {
            System.out.println("Client inexistant");
        }
    }

    public int nbClients() {
        return clients.size();
    }

    public Produit chercherProduit(int codeBarre) {
        for (Produit produit : produits) {
            if (produit.getCodeBarre() == codeBarre) {
                return produit;
            }
        }
        return null;
    }

    public void ajouterProduit(Produit produit) {
        if(chercherProduit(produit.getCodeBarre()) == null) {
            produits.add(produit);

        }else {
            System.out.println("Produit déjà existant");
        }
    }

    public void supprimerProduit(int codeBarre) {
        if (chercherProduit(codeBarre) != null) {
            produits.remove(chercherProduit(codeBarre));
        } else {
            System.out.println("Produit inexistant");
        }
    }

    public void modifierProduit(int codeBarre, String designation, int quantite, double prix, Categorie categorie) {
        if (chercherProduit(codeBarre) != null) {
            chercherProduit(codeBarre).setDesignation(designation);
            chercherProduit(codeBarre).setQuantite(quantite);
            chercherProduit(codeBarre).setPrix(prix);
            chercherProduit(codeBarre).setCategorie(categorie);
        } else {
            System.out.println("Produit inexistant");
        }
    }

    public int nbProduits() {
        return produits.size();
    }


    public void afficherClients() {
        for (Client client : clients) {
            client.afficher();
        }
    }

    public void afficherProduits() {
        for (Produit produit : produits) {
            produit.afficher();
        }
    }

    public void afficher() {
        System.out.println("Centre Commercial: " + nom + "\nAdresse: " + adresse);
        System.out.println("Clients: ");
        afficherClients();
        System.out.println("Produits: ");
        afficherProduits();
    }

    public void ajouterCommande(Commande commande) {
        commandes.add(commande);
    }

    public Client clientFedele() {
        // Map pour stocker la quantité totale commandée par chaque client
        Map<Client, Integer> clientTotalQuantity = new HashMap<>();

        // Calculer la quantité totale commandée par chaque client
        for (Commande commande : commandes) {
            Client client = commande.getClient();
            int quantiteCde = commande.getQuantiteCde();
            int totalQuantity = clientTotalQuantity.getOrDefault(client, 0);
            totalQuantity += quantiteCde;
            clientTotalQuantity.put(client, totalQuantity);
        }

        // Trouver le client avec la quantité totale commandée la plus élevée
        Client mostLoyalClient = null;
        int maxTotalQuantity = 0;
        for (Map.Entry<Client, Integer> entry : clientTotalQuantity.entrySet()) {
            Client client = entry.getKey();
            int totalQuantity = entry.getValue();
            if (totalQuantity > maxTotalQuantity) {
                mostLoyalClient = client;
                maxTotalQuantity = totalQuantity;
            }
        }

        return mostLoyalClient;
    }

    public Produit produitPlusVendu() {
        // Map pour stocker la quantité totale commandée pour chaque produit
        Map<Produit, Integer> produitTotalQuantity = new HashMap<>();

        // Calculer la quantité totale commandée pour chaque produit
        for (Commande commande : commandes) {
            Produit produit = commande.getProduit();
            int quantiteCde = commande.getQuantiteCde();
            int totalQuantity = produitTotalQuantity.getOrDefault(produit, 0);
            totalQuantity += quantiteCde;
            produitTotalQuantity.put(produit, totalQuantity);
        }

        // Trouver le produit avec la quantité totale commandée la plus élevée
        Produit bestSellingProduct = null;
        int maxTotalQuantity = 0;
        for (Map.Entry<Produit, Integer> entry : produitTotalQuantity.entrySet()) {
            Produit produit = entry.getKey();
            int totalQuantity = entry.getValue();
            if (totalQuantity > maxTotalQuantity) {
                bestSellingProduct = produit;
                maxTotalQuantity = totalQuantity;
            }
        }

        return bestSellingProduct;
    }
}

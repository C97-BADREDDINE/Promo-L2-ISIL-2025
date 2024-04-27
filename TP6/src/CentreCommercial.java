import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CentreCommercial implements Critere {

    private String nom;
    private String adresse;
    private ArrayList<Client> clients = new ArrayList<Client>();
    private ArrayList<Produit> produits = new ArrayList<Produit>();
    private ArrayList<Commande> commandes = new ArrayList<Commande>();

    public CentreCommercial() {
        this.nom = "";
        this.adresse = "";
    }

    public CentreCommercial(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<Produit> getProduits() {
        return produits;
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
        if (chercherClient(client.getNumero()) == null) {
            clients.add(client);

        } else {
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
        if (chercherProduit(produit.getCodeBarre()) == null) {
            produits.add(produit);

        } else {
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

    public void afficher(ArrayList<Client> clients) {
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    public void ajouterCommande(Commande commande, Client client, Produit produit) {
        if (commande.getQuantiteCde() > produit.getQuantite()) {
            System.out.println("Commande ajoutée avec échec !, quantité insuffisante");
            return;
        } else {

            commande.setProduit(produit);
            commande.setClient(client);
            if (this.chercherClient(client.getNumero()) == null) {
                this.ajouterClient(client);
            }
            commandes.add(commande);
            produit.setQuantite(produit.getQuantite() - commande.getQuantiteCde());
            System.out.println("Commande ajoutée avec succès !");
        }
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

    public boolean Ok(Object o) {
        try {
            if (o instanceof ArrayList<?>) {
                ArrayList<?> list = (ArrayList<?>) o;
                if (list.size() > 100 && list.get(0) instanceof Client) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

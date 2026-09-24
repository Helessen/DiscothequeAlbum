package Application;

import Modele.Album;
import Modele.GestionDisque;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
    static Scanner sc = new Scanner(System.in);

    public static int menu() {
        int choix = 0;

        System.out.println("***********************************");
        System.out.println("Que souhaitez-vous faire :");
        System.out.println("[1] Ajouter un album");
        System.out.println("[2] Lister tous les albums");
        System.out.println("[3] Rechercher un album");
        System.out.println("[4] Supprimer un album");
        System.out.println("[] Quitter");
        System.out.println("**********************************");
        System.out.print("Votre choix : ");
       choix = sc.nextInt();
        sc.nextLine();
        return choix;
    }

    public static void ajouterDisque(ArrayList<Album> album) throws AuteurException, DisqueException {

        System.out.println("Saisissez le nom du disque");
        String titre = sc.nextLine();
        System.out.println("Saisissez la date de sortie (jj/mm/aaaa) : ");
        String dateSaisie = sc.nextLine();

        if (titre.isEmpty() || dateSaisie.isEmpty()) {
            throw new DisqueException("Titre ou date sortie non renseigné");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate sortie = null;
        try {
            sortie = LocalDate.parse(dateSaisie, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Format de date invalide. Utilisez le format jj/mm/aaaa.");
            return; // ou gérer l'erreur selon ta logique
        }

        System.out.println("Saisissez le nom de l'auteur");
        String nom = sc.nextLine();

        System.out.println("Saisissez le prénom de l'auteur");
        String prenom = sc.nextLine();
        if (nom.isEmpty() || prenom.isEmpty()) {
            throw new AuteurException("Nom ou prénom auteur non renseigné");
        }

        GestionDisque.ajouterDisque(album);
    }

    public static void suppressionDisque() throws DisqueException {
        System.out.println("Entrez le nom du disque à supprimer :");
        String nomDisque = sc.nextLine();

        try {
            GestionDisque.supprimerDisque(nomDisque);
        } catch (DisqueException e) {
            if("MULTIPLE".equals(e.getMessage())) {
                System.out.println("Plusieurs disque portent ce nom. Veuillez préciser l'auteur");
                try {
                    suppressionDisqueAuteur(nomDisque);
                } catch (AuteurException ex) {
                    System.err.println("Erreur : " + ex.getMessage());
                }
            } else {
                System.err.println(e.getMessage());
            }
        }


    }


    public static void afficherDiscotheque() {
        ArrayList<Album> bibli = new ArrayList<>();
        bibli = GestionDisque.afficherDiscotheque();
        int cpt = 0;

        if(bibli.size() == 0) {
            System.out.println("*************************************************");
            System.out.println("Vous n'avez pas de disque dans votre discothèque.");
            System.out.println("*************************************************");
        } else {
            System.out.println("Voici les disques présent dans votre discothèque :");
            System.out.println("*****************************************************************************************");
            for (Disque item : bibli) {
                cpt += 1;
                System.out.println("[" + cpt + "] Titre : " + item.getNom() + " - Auteur : " + item.getAuteur() + " - Année : " + item.getAnnee());
            }
            System.out.println("*****************************************************************************************");
        }


    }

    public static void lireDisque(ArrayList<Disque> disque) {
        int cpt = 0;
        int choix = 0;
        System.out.println("Voici les disques présent dans votre discothèque :");
        System.out.println("*****************************************************************************************");
        for (Disque item : disque) {
            cpt += 1;
            System.out.println("[" + cpt + "] Titre : " + item.getNom() + " - Auteur : " + item.getAuteur() + " - Année : " + item.getAnnee());
        }
        System.out.println("*****************************************************************************************");
        try {
            System.out.println("Choisissez votre disque [entrez le numéro entre crochet]");
        } catch (InputMismatchException e) {
            //e.printStackTrace();
            System.out.println("Erreur de saisi, veuillez recomencer.");
            sc.nextLine();


        }
    }
}

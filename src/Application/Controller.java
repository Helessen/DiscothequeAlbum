package Application;

import Modele.Album;
import Exception.*;
import Modele.DisqueVinyle;
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
        System.out.println("[1] Consulter la discothèque");
        System.out.println("[2] Ajouter un disque");
        System.out.println("[3] Supprimer un disque");
        System.out.println("[4] Lire un disque");
        System.out.println("[5] Quitter");
        System.out.println("**********************************");
        choix = sc.nextInt();
        sc.nextLine();
        return choix;
    }

    public static void ajouterDisque() throws AlbumDejaExistantException, SaisieInvalideException, DoublonException {

        System.out.println("Type d'album (1 = CD, 2 = Vinyle, 3 = Fichier numérique");
        System.out.println("Saisissez le nom du disque");
        String titre = sc.nextLine();
        System.out.println("Saisissez la date de sortie (jj/mm/aaaa) : ");

        String dateSaisie = sc.nextLine();
        int reponse = sc.nextInt();
        if (reponse == 1) {

        }



        if (titre.isEmpty() || dateSaisie.isEmpty()) {
            throw new SaisieInvalideException("Saisie invalide");
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

        if (nom.isEmpty() || nom.isEmpty()) {
            throw new SaisieInvalideException("Nom de l'auteur non renseigné");
        }

        Album album = new Album(titre, auteur, sortie, quantite);
        GestionDisque.ajouterDisque(album);
    }

    public static void suppressionDisque() throws AlbumIntrouvableException, SaisieInvalideException {
        System.out.println("Entrez le nom du disque à supprimer :");
        String nom = sc.nextLine();

        try {
            GestionDisque.supprimerDisque(nom);
        } catch (DoublonException e) {
            if("MULTIPLE".equals(e.getMessage())) {
                System.out.println("Plusieurs disque portent ce nom. Veuillez préciser l'auteur");
                try {
                    suppressionDisqueAuteur(nom);
                } catch (SaisieInvalideException ex) {
                    System.err.println("Erreur : " + ex.getMessage());
                }
            } else {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void suppressionDisqueAuteur(String nomDisque) throws AlbumIntrouvableException, SaisieInvalideException {

        System.out.println("Quel est le nom de l'auteur ?");
        String nomA = sc.nextLine();
        System.out.println("Quel est le prénom de l'auteur ?");
        String prenomA = sc.nextLine();
        if (nomA.isEmpty() || prenomA.isEmpty()) {
            throw new SaisieInvalideException("Nom ou prénom non renseigné.");
        }
        GestionDisque.supprimerDisqueAuteur(nomA, prenomA);
    }

    public static void afficherDiscotheque() {
        ArrayList<Album> listeAlbum = new ArrayList<>();
        listeAlbum = GestionDisque.afficherDiscotheque();
        int cpt = 0;

        if(listeAlbum.size() == 0) {
            System.out.println("*************************************************");
            System.out.println("Vous n'avez pas de disque dans votre discothèque.");
            System.out.println("*************************************************");
        } else {
            System.out.println("Voici les disques présent dans votre discothèque :");
            System.out.println("*****************************************************************************************");
            for (Album item : listeAlbum) {
                cpt += 1;
                System.out.println("[" + cpt + "] Titre : " + item.getNom() + " - Auteur : " + item.getAuteur() + " - Année : " + item.getSortie());
            }
            System.out.println("*****************************************************************************************");
        }


    }

    public static void lireDisque(ArrayList<Album> disque) {
        int cpt = 0;
        int choix = 0;
        System.out.println("Voici les disques présent dans votre discothèque :");
        System.out.println("*****************************************************************************************");
        for (Album item : disque) {
            cpt += 1;
            System.out.println("[" + cpt + "] Titre : " + item.getNom() + " - Auteur : " + item.getAuteur() + " - Année : " + item.getSortie());
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

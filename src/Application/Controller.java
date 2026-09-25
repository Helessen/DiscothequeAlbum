package Application;

import Modele.*;
import Exception.SaisieInvalideException;
import Exception.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Controller {
    static Scanner sc = new Scanner(System.in);

    public static int menu() {
        int choix;

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

    public static void ajouterDisque() throws SaisieInvalideException {
        String titre;
        try {
            System.out.println("Saisissez le nom du disque");
            titre = sc.nextLine();
        } catch (SaisieInvalideException e) {
            throw new SaisieInvalideException("Le titre de l'album est obligatoire.");
        }

        /*if (titre.isEmpty()) {

        }*/

        System.out.println("Saisissez la date de sortie (jj/mm/aaaa) : ");
        String dateSaisie = sc.nextLine();

        if (dateSaisie.isEmpty()) {
            throw new SaisieInvalideException("La date de sortie est obligatoire.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate sortie = null;
        try {
            sortie = LocalDate.parse(dateSaisie, formatter);
        } catch (DateTimeParseException e) {
            throw new SaisieInvalideException("La date doit être au format jj/mm/aaaa.");
        }

        System.out.println("Saisissez le nom du chanteur/groupe.");
        String nom = sc.nextLine();

        if (nom.isEmpty()) {
            throw new SaisieInvalideException("Nom du chanteur/groupe non renseigné");
        }

        System.out.println("Quel est la quantité de disque dans l'album ? (tapper 1 si numérique)");
        int quantite = 0;
        try {
            quantite = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            sc.nextLine();
            throw new SaisieInvalideException("La quantité doit être un nombre entier valide.");
        }

        if(quantite <= 0) {
            throw new SaisieInvalideException("La quantité doit être supérieur à 0.");
        }

        System.out.println("Quel est votre type d'Album ?");
        System.out.println("[1]CD - [2]Vinyle - [3]Numérique");
        int choix = sc.nextInt();
        sc.nextLine();
        Album nouveauDisque = null;

        switch (choix) {
            case 1 :
                System.out.println("Quel est le numéro du CD ?");
                String numero = sc.nextLine();
                if(numero.isEmpty()) {
                    throw new SaisieInvalideException("Renseigner un numéro.");
                }
                System.out.println("Quel est le type d'album ?");
                String type = sc.nextLine();
                if(type.isEmpty()){
                    throw new SaisieInvalideException("Type de CD non rempli.");
                }
                nouveauDisque = new CompactDisque(titre, nom, sortie, quantite, numero,type);
                break;
            case 2:
                System.out.println("Quel est le numéro de vinyle ?");
                String numeroV = sc.nextLine();
                int tailleV = 0;
                if(numeroV.isEmpty()){
                    throw new SaisieInvalideException("Renseigner un numéro.");
                }
                System.out.println("Quel est la taille du vinyle ?");
                try {
                    tailleV = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    sc.nextLine();
                    throw new SaisieInvalideException("La quantité doit être un nombre entier valide.");
                }
                if(tailleV <= 0) {
                    throw new SaisieInvalideException("La valeur ne peut-être inférieur à 0.");
                }
                nouveauDisque = new DisqueVinyle(titre, nom, sortie, quantite, numeroV, tailleV);
                break;
            case 3 :
                System.out.println("Quel est le format du fichier numérique ?");
                String format = sc.nextLine();
                if(format.isEmpty()){
                    throw new SaisieInvalideException("Format non renseigné.");
                }
                System.out.println("Quelle est la taille du fichier audio (en MO) ?");
                double taille = 0;
                try {
                    taille = sc.nextDouble();
                    sc.nextLine();
                } catch(InputMismatchException e) {
                    sc.nextLine();
                    throw new SaisieInvalideException("Erreur dans la saisie de la valeur.");
                }
                if(taille <= 0) {
                    throw new SaisieInvalideException("La valeur ne peut-être inférieur à 0.");
                }
                int duree = 0;
                System.out.println("Combien de temps durent le fichier ? (seconde ou min)");
                try {
                    duree = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    sc.nextLine();
                    throw new SaisieInvalideException("La quantité doit être un nombre entier valide");
                }
                if(duree <= 0) {
                    throw new SaisieInvalideException("La valeur ne peut-être inférieur à 0.");
                }
                nouveauDisque = new FichierNumerique(titre, nom, sortie, quantite, format, taille, duree);
                break;
            default:
                throw new SaisieInvalideException("Choix de type d'album invalide (choisir entre 1 et 3).");
        }

        boolean ajout = GestionDisque.ajouterDisque(nouveauDisque);

        if(ajout) {
            System.out.println("Le disque a été enregistré avec succès dans la discothèque.");
        } else {
            System.out.println("Disque déjà présent dan la discothèque.");
        }
    }

    public static void suppressionDisque() throws AlbumIntrouvableException {
        System.out.println("Entrez le nom du disque à supprimer :");
        String nomDisque = sc.nextLine();

        try {
            GestionDisque.supprimerDisque(nomDisque);
        } catch (AlbumIntrouvableException e) {
            if("MULTIPLE".equals(e.getMessage())) {
                System.out.println("Plusieurs disque portent ce nom. Veuillez préciser l'auteur");
                try {
                    //suppressionDisqueAuteur(nomDisque);
                } catch (AlbumIntrouvableException ex) {
                    System.err.println("Erreur : " + ex.getMessage());
                }
            } else {
                System.err.println(e.getMessage());
            }
        }
    }


   public static void afficherDiscotheque() throws DiscothequeVideException {
        ArrayList<Album> bibli = new ArrayList<>();
        bibli = GestionDisque.afficherDiscotheque();
        int cpt = 0;

        if(bibli.size() == 0) {
            throw new DiscothequeVideException ("\n*************************************************\nVous n'avez pas de disque dans votre discothèque.\n*************************************************");
        } else {
            System.out.println("Voici les disques présent dans votre discothèque :");
            System.out.println("*****************************************************************************************");
            for (Album item : bibli) {
                cpt += 1;
                System.out.println("[" + cpt + "] Titre : " + item.getNom() + " - Auteur : " + item.getAuteur() + " - Année : " + item.getSortie() + " - Type :" + item.getSupport());
            }
            System.out.println("*****************************************************************************************");
        }
    }
/*
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
    }*/
}

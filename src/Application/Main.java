package Application;

import static Application.Controller.menu;
import Exception.SaisieInvalideException;

public class Main {
    static void main() {
        boolean bool = false;
        int choix = 0;

        while (!bool) {
            try {
                choix = menu();
                switch (choix) {
                    case 1 :
                        Controller.ajouterDisque();
                        break;
                    case 2 :
                        //Controller.afficherDiscotheque();
                        break;
                    case 3 :
                        //Controller.rechercherAlbum();
                        break;
                    case 4 :
                        Controller.suppressionDisque();
                        break;
                    case 0 :
                        System.out.println("Fin du programme. Au revoir !");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Choix invalide, veuillez réessayer.");
                        break;
                }

            } catch (SaisieInvalideException e) {
                System.out.println("\n Erreur : " +e.getMessage() + "\n");
            } catch (Exception e) {
                System.out.println(" \n Une erreur inatendu est survenue");
            }
        }
    }
}

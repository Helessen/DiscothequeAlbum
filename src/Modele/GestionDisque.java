package Modele;

import java.util.ArrayList;
import Exception.AlbumDejaExistantException;
import Exception.DoublonException;

public class GestionDisque {
    private static ArrayList<Album> discotheque = new ArrayList<>();

    public static void ajouterDisque(Album disque) throws AlbumDejaExistantException {
        for(Album d : discotheque){
            if (d.getNom().equalsIgnoreCase(disque.getNom()) && d.getAuteur().equalsIgnoreCase(disque.getAuteur())
                    ) {

                throw new AlbumDejaExistantException("Le disque existe déjà !");
            }
        }
        discotheque.add(disque);
    }

    public static void supprimerDisque(String nomDisque) throws DoublonException {
        ArrayList<Album> disqueTrouve = new ArrayList<>();
        boolean multiD = false;

        for (Album item : discotheque) {
            if (item.getNom().equalsIgnoreCase(nomDisque)) {
                disqueTrouve.add(item);
            }
        }
        if(disqueTrouve.isEmpty()){
            System.out.println("Aucun disque trouvé avec ce titre.");
        } else if (disqueTrouve.size() == 1) {
            Album a = disqueTrouve.get(0);
            discotheque.remove(a);
            System.out.println("l'album " + a.getNom() + " du chanteur " + a.getAuteur() + " a bien été supprimé.");
        } else {
            throw new DoublonException("MULTIPLE");
            // Controller.suppressionDisqueAuteur(nomDisque); // On ne peux pas atteindre ce code car le throw l'en empêche
        }
    }
    public static void supprimerDisqueAuteur(String titre, String nomA) {
        Album aSupprimer = null;

        for(Album item: discotheque){
            if(item.getNom().equalsIgnoreCase(titre) && item.getAuteur().equalsIgnoreCase(nomA)) {
                aSupprimer = item;
                break;
            }
        }
        if(aSupprimer != null) {
            discotheque.remove(aSupprimer);
            System.out.println("L'album " + aSupprimer.getNom() + " de " + aSupprimer.getAuteur() + " a bien été supprimé.");
        } else {
            System.out.println("Aucun album correspondant à cet auteur n'a été trouvé.");
        }
    }

    public static ArrayList<Album> afficherDiscotheque() {
        return discotheque;
    }

}

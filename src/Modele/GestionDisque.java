package Modele;

import java.util.ArrayList;
import Exception.AlbumDejaExistantException;

public class GestionDisque {
    private static ArrayList<Album> discotheque = new ArrayList<>();

    public static boolean ajouterDisque(Album album) throws AlbumDejaExistantException {

        for(Album d : discotheque){
            if (d.getNom().equalsIgnoreCase(album.getNom()) && d.getAuteur().equalsIgnoreCase(album.getAuteur())
                    ) {
                return false;
            }
        }
        discotheque.add(album);
        return  true;
    }

    public static boolean supprimerDisque(String nomDisque) {
        boolean multiD = false;
        Album a = null;

        for (Album item : discotheque) {
            if (item.getNom().equalsIgnoreCase(nomDisque)) {
                a = item;
            }
        }
        if(a != null) {
            discotheque.remove(a);
            return true;
        }
        return false;
    }

    public static ArrayList<Album> afficherDiscotheque() {
        return discotheque;
    }

    public static Album rechercheDisque(String titre) {
        //soit retourne un objet, soit retourne objet = null
        Album a = null;

        for(Album item : discotheque) {
            if(item.getNom().equalsIgnoreCase(titre)) {
                a = item;
            }
        } return a;
    }

    public static boolean viderDiscotheque() {
        if(discotheque.size() == 0) {
            return false;
        } else {
            discotheque.clear();
        }
        return true;
    }

    public static Album rechercherAlbum(String titre) {
        Album a = null;
        for(Album item : discotheque){
            if(titre.equalsIgnoreCase(item.getNom())) {
                a = item;
            }
        }
        return a;
    }

}

package Modele;

import java.util.Date;

public abstract class Album {
    String nom;
    String auteur;
    Date sortie;
    int quantite;

    public Album(String nom, String auteur, Date sortie, int quantite) {
        this.nom = nom;
        this.auteur = auteur;
        this.sortie = sortie;
        this.quantite = quantite;
    }
}

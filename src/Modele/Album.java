package Modele;

import java.time.LocalDate;


public abstract class Album {
    private String nom;
    private String auteur;
    private LocalDate sortie;
    private int quantite;

    public Album(String nom, String auteur, LocalDate sortie, int quantite) {
        this.nom = nom;
        this.auteur = auteur;
        this.sortie = sortie;
        this.quantite = quantite;
    }

    public abstract String getSupport();

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public LocalDate getSortie() {
        return sortie;
    }

    public void setSortie(LocalDate sortie) {
        this.sortie = sortie;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Album{" +
                "nom='" + nom + '\'' +
                ", auteur='" + auteur + '\'' +
                ", sortie=" + sortie +
                ", quantite=" + quantite +
                '}';
    }
}

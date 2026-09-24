package Modele;
import java.util.Date;

public class DisqueVinyle extends Album {
    private String numero;
    private int taille;

    public DisqueVinyle(String nom, String auteur, Date sortie, int quantite, String numero, int taille) {
        super(nom, auteur, sortie, quantite);
        this.numero = numero;
        this.taille = taille;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    @Override
    public String toString() {
        return "DisqueVinyle{" +
                "numero=" + numero +
                ", taille=" + taille +
                '}';
    }
}

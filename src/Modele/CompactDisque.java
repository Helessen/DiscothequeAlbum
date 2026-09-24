package Modele;

import java.time.LocalDate;

public class CompactDisque extends Album{
    private int numero;
    private String type;

    public CompactDisque(String nom, String auteur, LocalDate sortie, int quantite, int numero, String type) {
        super(nom, auteur, sortie, quantite);
        this.numero = numero;
        this.type = type;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CompactDisque{" +
                "numero=" + numero +
                ", type=" + type +
                '}';
    }
}

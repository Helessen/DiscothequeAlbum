package Modele;

import java.util.Date;

public class CompactDisque extends Album{
    int numero;
    int type;

    public CompactDisque(String nom, String auteur, Date sortie, int quantite, int numero, int type) {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
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

package Modele;

import java.time.LocalDate;

public class CompactDisque extends Album{
    private String numero;
    private String type;

    public CompactDisque(String nom, String auteur, LocalDate sortie, int quantite, String numero, String type) {
        super(nom, auteur, sortie, quantite);
        this.numero = numero;
        this.type = type;
    }

    @Override
    public String getSupport() {
        return "CD";
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
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
                "numero='" + numero + '\'' +
                ", type='" + type + '\'' +
                '}' + super.toString() +
                getSupport();
    }
}

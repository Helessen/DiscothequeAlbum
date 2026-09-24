package Modele;

public class FichierNumerique extends Album {
    private String format;
    private double taille;
    private int duree;



    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "FichierNumerique{" +
                "format='" + format + '\'' +
                ", taille=" + taille +
                ", duree=" + duree +
                '}';
    }


}

package ch.heig.pl.dto;

public class Contact {
    private String nom;
    private int telephone;

    public Contact() {
    }

    public Contact(String nom, int telephone) {
        this.nom = nom;
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
}

package ch.heig.pl.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Contact {
    @Id
    @GeneratedValue
    private int id;
    @Column(length=15)
    private String nom;
    private int telephone;

    @OneToOne
    private Contact conjoint;

    public Contact() {
    }

    public Contact(String nom, int telephone) {
        this.nom = nom;
        this.telephone = telephone;
    }

    public Contact getConjoint() {
        return conjoint;
    }

    public void setConjoint(Contact conjoint) {
        this.conjoint = conjoint;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getNom() {
        return nom;
    }

    public int getTelephone() {
        return telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return nom.equals(contact.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "nom='" + nom + '\'' +
                ", telephone=" + telephone +
                '}';
    }
}

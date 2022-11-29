package ch.heig.pl.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "contacts")
public class ContactEntity {
    @Id
    @GeneratedValue
    private int id;
    @Column(length=15)
    private String nom;
    private int telephone;

    @OneToOne
    private ContactEntity conjoint;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    private List<ContactEntity> enfants;

    @ManyToOne
    @JoinColumn
    private ContactEntity parent;

    public ContactEntity() {
    }

    public ContactEntity(String nom, int telephone) {
        this.nom = nom;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContactEntity getConjoint() {
        return conjoint;
    }

    public void setConjoint(ContactEntity conjoint) {
        this.conjoint = conjoint;
    }
    public void addEnfant(ContactEntity contactEntity) {
        enfants.add(contactEntity);
    }
    public List<ContactEntity> getEnfants() {
        return enfants;
    }

    public void setEnfants(List<ContactEntity> enfants) {
        this.enfants = enfants;
    }

    public ContactEntity getParent() {
        return parent;
    }

    public void setParent(ContactEntity parent) {
        this.parent = parent;
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
        ContactEntity contactEntity = (ContactEntity) o;
        return nom.equals(contactEntity.nom);
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

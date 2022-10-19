package ch.heig.pl.integration;

import ch.heig.pl.model.Contact;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ContactDAO {

    @PersistenceContext(unitName = "ContactsPersistenceUnit")
    private EntityManager em;

    /**
     * Liste de tous les contacts
     */
    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        try {
            contacts = em.createQuery("SELECT c FROM Contact c", Contact.class).getResultList();
        } catch (PersistenceException e) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return contacts;
    }

    /**
     *  Crée ou met à jour un Contact
     */
    public void save(Contact contact) {
            em.merge(contact);
    }

    /**
     * Obtention d'un contact
     *   obtention du contact conjoint s'il existe
     *   (contact inconnu encore à gérer)
     */
    public Contact getContact(String nom) {
        Contact contact = null;
        try {
            TypedQuery<Contact> query = em.createQuery(
                    "SELECT c FROM Contact c WHERE c.nom = :nom", Contact.class);
            return query.setParameter("nom", nom).getSingleResult();
        } catch (PersistenceException e) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return contact;
    }
}

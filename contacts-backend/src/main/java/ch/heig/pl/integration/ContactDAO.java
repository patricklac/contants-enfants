package ch.heig.pl.integration;

import ch.heig.pl.model.ContactEntity;

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
    public List<ContactEntity> getContacts() {
        List<ContactEntity> contactEntities = new ArrayList<>();
        try {
            contactEntities = em.createQuery("SELECT c FROM ContactEntity c", ContactEntity.class).getResultList();
        } catch (PersistenceException e) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return contactEntities;
    }

    /**
     *  Crée ou met à jour un Contact
     */
    public void save(ContactEntity contactEntity) {
            em.merge(contactEntity);
    }

    /**
     * Obtention d'un contact
     *   obtention du contact conjoint s'il existe
     *   (contact inconnu encore à gérer)
     */
    public ContactEntity getContact(String nom) {
        ContactEntity contactEntity = null;
        try {
            TypedQuery<ContactEntity> query = em.createQuery(
                    "SELECT c FROM ContactEntity c WHERE c.nom = :nom", ContactEntity.class);
            return query.setParameter("nom", nom).getSingleResult();
        } catch (PersistenceException e) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return contactEntity;
    }
}

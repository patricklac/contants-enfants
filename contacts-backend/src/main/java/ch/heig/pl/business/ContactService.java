package ch.heig.pl.business;

import ch.heig.pl.dto.Couple;
import ch.heig.pl.integration.ContactDAO;
import ch.heig.pl.model.Contact;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class ContactService {

    @Inject
    ContactDAO contactDAO;

    /**
     *  Unit 2 contacts s'ils existent et s'ils sont libres
     */
    public void unit(String nom1, String nom2) throws AlreadyCoupledException {
        Contact contact1 = contactDAO.getContact(nom1);
        Contact contact2 = contactDAO.getContact(nom2);
        if (contact1 != null && contact1.getConjoint() == null
                && contact2 != null && contact2.getConjoint() == null) {
            contact1.setConjoint(contact2);
            contactDAO.save(contact1);
            contact2.setConjoint(contact1);
            contactDAO.save(contact2);
        } else {
            throw new AlreadyCoupledException();
        }
    }

    /**
     * Liste des contacts en couple.
     *   seulement un contact par couple.
     */
    public List<Couple> getCouples() {
        List<Contact> contacts = contactDAO.getContacts();
        List<Couple> couples = new ArrayList<>();
        Set<Contact> contactsInCouple = new HashSet<>();
        for (Contact contact : contacts) {
            Contact contact2 = contact.getConjoint();
            if (contact2 != null && !contactsInCouple.contains(contact2)) {
                contactsInCouple.add(contact);
                couples.add(new Couple(contact.getNom(),contact2.getNom()));
            }
        }
        return couples;
    }
}

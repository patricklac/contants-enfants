package ch.heig.pl.business;

import ch.heig.pl.dto.Couple;
import ch.heig.pl.integration.ContactDAO;
import ch.heig.pl.model.ContactEntity;

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
        ContactEntity contactEntity1 = contactDAO.getContact(nom1);
        ContactEntity contactEntity2 = contactDAO.getContact(nom2);
        if (contactEntity1 != null && contactEntity1.getConjoint() == null
                && contactEntity2 != null && contactEntity2.getConjoint() == null) {
            contactEntity1.setConjoint(contactEntity2);
            contactDAO.save(contactEntity1);
            contactEntity2.setConjoint(contactEntity1);
            contactDAO.save(contactEntity2);
        } else {
            throw new AlreadyCoupledException();
        }
    }

    /**
     * Liste des contacts en couple.
     *   seulement un contact par couple.
     */
    public List<Couple> getCouples() {
        List<ContactEntity> contactEntities = contactDAO.getContacts();
        List<Couple> couples = new ArrayList<>();
        Set<ContactEntity> contactsInCouple = new HashSet<>();
        for (ContactEntity contactEntity : contactEntities) {
            ContactEntity contactEntity2 = contactEntity.getConjoint();
            if (contactEntity2 != null && !contactsInCouple.contains(contactEntity2)) {
                contactsInCouple.add(contactEntity);
                couples.add(new Couple(contactEntity.getNom(), contactEntity2.getNom()));
            }
        }
        return couples;
    }
}

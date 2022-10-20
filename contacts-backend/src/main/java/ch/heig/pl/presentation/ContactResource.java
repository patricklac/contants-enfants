package ch.heig.pl.presentation;

import ch.heig.pl.dto.Contact;
import ch.heig.pl.integration.ContactDAO;
import ch.heig.pl.model.ContactEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/contact")
public class ContactResource  {
    @Inject
    private ContactDAO contactDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Contact> getContacts () {
        List<ContactEntity> contactsEntities = contactDAO.getContacts();
        List<Contact> contacts = new ArrayList<>();
        for(ContactEntity contactEntity : contactsEntities) {
            contacts.add(new Contact(contactEntity.getNom(),contactEntity.getTelephone()));
        }
        return contacts;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(Contact contact) {
        ContactEntity contactEntity = new ContactEntity(contact.getNom(),contact.getTelephone());
        contactDAO.save(contactEntity);
    }
}

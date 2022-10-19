package ch.heig.pl.presentation;

import ch.heig.pl.business.ContactService;
import ch.heig.pl.integration.ContactDAO;
import ch.heig.pl.model.Contact;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/contact")
public class ContactResource  {
    @Inject
    private ContactDAO contactDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Contact> getContacts () {
        return contactDAO.getContacts();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(Contact contact) {
        contactDAO.save(contact);
    }
}

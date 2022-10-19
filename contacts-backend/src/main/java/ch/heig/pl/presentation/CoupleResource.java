package ch.heig.pl.presentation;

import ch.heig.pl.business.AlreadyCoupledException;
import ch.heig.pl.business.ContactService;
import ch.heig.pl.integration.ContactDAO;
import ch.heig.pl.model.Contact;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;

@Path("/couple")
public class CoupleResource {
    @Inject
    private ContactDAO contactDAO;
    @Inject
    private ContactService contactService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Contact> getContacts () {
        return contactService.getContactsInCouple();
    }

//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void unit(String nom1, String nom2) {
//        try {
//            contactService.unit(nom1, nom2);
//        } catch (AlreadyCoupledException e) {
//            throw new RuntimeException(e);
//        }
//    }
}

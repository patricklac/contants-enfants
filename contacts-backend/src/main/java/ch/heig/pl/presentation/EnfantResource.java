package ch.heig.pl.presentation;

import ch.heig.pl.business.AlreadyCoupledException;
import ch.heig.pl.business.ContactService;
import ch.heig.pl.dto.Contact;
import ch.heig.pl.dto.Couple;
import ch.heig.pl.integration.ContactDAO;
import ch.heig.pl.model.ContactEntity;
import ch.heig.pl.model.ContactNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/enfant")
public class EnfantResource {
    @Inject
    private ContactDAO contactDAO;
    @Inject
    private ContactService contactService;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void enfant(Couple couple) {
        try {
            contactService.enfant(couple.getNom1(), couple.getNom2());
        } catch (ContactNotFoundException e) {
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Contact> getEnfants (@PathParam("id") String id) {
        ContactEntity contactEntity = null;
        contactEntity = contactDAO.getContactById(id);
        List<ContactEntity> enfantEntities = contactEntity.getEnfants();
        List<Contact> contacts = new ArrayList<>();
        for(ContactEntity enfantEntity : enfantEntities) {
            contacts.add(new Contact(enfantEntity.getNom(),enfantEntity.getTelephone()));
        }
        return contacts;
    }
}

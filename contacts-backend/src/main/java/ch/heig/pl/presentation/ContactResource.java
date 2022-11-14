package ch.heig.pl.presentation;

import ch.heig.pl.dto.Contact;
import ch.heig.pl.integration.ContactDAO;
import ch.heig.pl.model.ContactEntity;
import ch.heig.pl.model.ContactNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
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

    @GET
    @Path("/{nom}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContact (@PathParam("nom") String nom) {
        ContactEntity contactEntity = null;
        try {
            contactEntity = contactDAO.getContact(nom);
        } catch (ContactNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
        Contact contact = new Contact(contactEntity.getNom(),contactEntity.getTelephone());
        return Response.ok().entity(contact).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Contact contact, @Context UriInfo uriInfo) {
        ContactEntity contactEntity = new ContactEntity(contact.getNom(),contact.getTelephone());
        contactDAO.save(contactEntity);
        URI location = uriInfo.getRequestUriBuilder()
                .path(contact.getNom())
                .build();
        return Response.created(location).build();
    }
}

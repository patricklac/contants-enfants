package ch.heig.pl.presentation;

import ch.heig.pl.business.AlreadyCoupledException;
import ch.heig.pl.business.ContactService;
import ch.heig.pl.dto.Couple;
import ch.heig.pl.integration.ContactDAO;
import ch.heig.pl.model.ContactNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/couple")
public class CoupleResource {
    @Inject
    private ContactDAO contactDAO;
    @Inject
    private ContactService contactService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Couple> getCouples () {
        return contactService.getCouples();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void unit(Couple couple) {
        try {
            contactService.unit(couple.getNom1(), couple.getNom2());
        } catch (AlreadyCoupledException | ContactNotFoundException e) {
            throw new NotFoundException();
        }
    }
}

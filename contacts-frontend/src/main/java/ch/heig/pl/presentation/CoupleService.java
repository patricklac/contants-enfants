package ch.heig.pl.presentation;

import ch.heig.pl.dto.Couple;
import ch.heig.pl.model.Contact;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;

@Path("/api")
@RegisterRestClient(baseUri = "http://localhost:8081")
public interface CoupleService {
    @GET
    @Path("/couple")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Couple> getCouples ();

    @POST
    @Path("/couple")
    @Produces(MediaType.APPLICATION_JSON)
    public void unit(Couple couple);
}

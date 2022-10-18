package ch.heig.pl.presentation;

import ch.heig.pl.model.Contact;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api")
@RegisterRestClient(baseUri = "http://localhost:8081")
public interface ContactService {
    @GET
    @Path("/contact")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Contact> getContacts ();

    @POST
    @Path("/contact")
    @Produces(MediaType.APPLICATION_JSON)
    public void add(Contact contact);
}

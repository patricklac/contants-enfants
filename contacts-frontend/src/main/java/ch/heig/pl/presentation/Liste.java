package ch.heig.pl.presentation;

import ch.heig.pl.model.Contact;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@WebServlet("/liste")
public class Liste extends HttpServlet {

    @Inject
    @RestClient
    ContactService service;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            URI apiUri = new URI("http://localhost:8080/contactsapi/api");
//            ContactService service = RestClientBuilder.newBuilder()
//                    .baseUri(apiUri)
//                    .build(ContactService.class);
            List<Contact> contacts = service.getContacts();
            request.setAttribute("contacts", contacts);
            request.getRequestDispatcher("/WEB-INF/pages/liste.jsp").forward(request, response);
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }

    }
}

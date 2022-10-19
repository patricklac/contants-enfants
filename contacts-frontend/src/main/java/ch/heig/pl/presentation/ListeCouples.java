package ch.heig.pl.presentation;

import ch.heig.pl.dto.Couple;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listeCouples")
public class ListeCouples extends HttpServlet {
    @Inject
    @RestClient
    private CoupleService coupleService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Couple> couples = coupleService.getCouples();
        request.setAttribute("couples", couples);
        request.getRequestDispatcher("/WEB-INF/pages/listeCouples.jsp").forward(request, response);
    }
}

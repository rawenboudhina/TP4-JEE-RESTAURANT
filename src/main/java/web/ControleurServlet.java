package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import dao.IplatDao;
import dao.PlatDaoImpl;
import metier.entities.Plat;

@WebServlet(name = "cs", urlPatterns = { "/controleur", "*.do" })
public class ControleurServlet extends HttpServlet {

    IplatDao metier;

    @Override
    public void init() throws ServletException {
        metier = new PlatDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/index.do")) {
            request.getRequestDispatcher("plats.jsp").forward(request, response);
        } else if (path.equals("/chercher.do")) {
            String motCle = request.getParameter("motCle");
            PlatModele model = new PlatModele();
            model.setMotCle(motCle);
            List<Plat> plats = metier.platsParMC(motCle);
            model.setPlats(plats);
            request.setAttribute("model", model);
            request.getRequestDispatcher("plats.jsp").forward(request, response);
        } else if (path.equals("/saisie.do")) {
            request.getRequestDispatcher("SaisiePlat.jsp").forward(request, response);
        } else if (path.equals("/save.do") && request.getMethod().equals("POST")) {
            String nom = request.getParameter("nom");
            double prix = Double.parseDouble(request.getParameter("prix"));
            Plat p = metier.save(new Plat(nom, prix));
            request.setAttribute("plat", p);
            request.getRequestDispatcher("confirmation.jsp").forward(request, response);
        } else if (path.equals("/supprimer.do")) {
            String idParam = request.getParameter("id");
            if (idParam != null && !idParam.isEmpty()) {
                Long id = Long.parseLong(idParam);
                metier.deletePlat(id);
                response.sendRedirect("chercher.do?motCle=");
            } else {
                System.out.println("erreur");
            }
        } else if (path.equals("/editer.do")) {
            Long id = Long.parseLong(request.getParameter("id"));
            Plat p = metier.getPlat(id);
            request.setAttribute("plat", p);
            request.getRequestDispatcher("editerPlat.jsp").forward(request, response);
        } else if (path.equals("/update.do")) {
            Long id = Long.parseLong(request.getParameter("id"));
            String nom = request.getParameter("nom");
            double prix = Double.parseDouble(request.getParameter("prix"));
            Plat p = new Plat();
            p.setIdPlat(id);
            p.setNomPlat(nom);
            p.setPrix(prix);
            metier.updatePlat(p);
            request.setAttribute("plat", p);
            request.getRequestDispatcher("confirmation.jsp").forward(request, response);
        } else {
            response.sendError(Response.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

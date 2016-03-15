/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laferme.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import laferme.entity.Utilisateur;
import laferme.exception.UtilisateurDejaInscrit;
import laferme.service.ConnexionService;
import laferme.service.InscriptionService;
import laferme.spring.AutowireServlet;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
@WebServlet(name = "InscriptionServlet", urlPatterns = {"/inscription"})
public class InscriptionServlet extends AutowireServlet {

    @Autowired
    InscriptionService inscriptionService;
    
    @Autowired
    ConnexionService connexionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("_CSS.jsp").include(req, resp);
        req.getRequestDispatcher("_HEADER.jsp").include(req, resp);
        req.getRequestDispatcher("Formulaire_inscription.jsp").include(req, resp);
        req.getRequestDispatcher("_FOOTER.jsp").include(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utilisateur u = new Utilisateur();
        u.setEmail(req.getParameter("email"));
        u.setMdp(req.getParameter("mdp"));
        try {
            inscriptionService.inscriptionUtilisateur(u);
            req.setAttribute("email", req.getParameter("email"));
            req.setAttribute("mdp", req.getParameter("mdp"));
            resp.sendRedirect("login");
        } catch (UtilisateurDejaInscrit ex) {
            String information = "Vous êtes déjà inscrit";
            req.setAttribute("info", information);
            req.getRequestDispatcher("login").forward(req, resp);
            return;
        }
    }
}

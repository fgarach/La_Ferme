/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laferme.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import laferme.entity.Ressource;
import laferme.entity.Utilisateur;
import laferme.enumeration.TypeEtat;
import laferme.enumeration.TypeRessource;
import laferme.service.ActualisationService;
import laferme.service.RessourceCrudService;
import laferme.service.UtilisateurCrudService;
import laferme.spring.AutowireServlet;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
@WebServlet(name = "ActualisationServlet", urlPatterns = {"/actualisation"})
public class ActualisationServlet extends AutowireServlet {

    @Autowired
    private ActualisationService actualisationService;

    @Autowired
    private UtilisateurCrudService utilisateurService;

    @Autowired
    private RessourceCrudService ressourceCrudService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getSession().getAttribute("email").toString();
        Utilisateur u = utilisateurService.findByEmail(email);
        actualisationService.actualiser(u.getId());

        List<Ressource> fermiers = ressourceCrudService.findByTypeRessourceAndTypeEtatAndUtilisateurId(TypeRessource.FERMIER, TypeEtat.VIVANT, u.getId());

        if (fermiers.isEmpty()) {
            resp.sendRedirect("game_over");
        } else {
            req.getRequestDispatcher("plateforme").include(req, resp);
        }
    }

}

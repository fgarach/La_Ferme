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
import laferme.service.InitialiserPlateformeService;
import laferme.service.UtilisateurCrudService;
import laferme.spring.AutowireServlet;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
@WebServlet(name = "StartServlet", urlPatterns = {"/start"})
public class StartServlet extends AutowireServlet {

    @Autowired
    private InitialiserPlateformeService initialiserService;

    @Autowired
    private UtilisateurCrudService utilisateurService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String start = req.getParameter("start");
        String email = req.getSession().getAttribute("email").toString();
        Utilisateur u = utilisateurService.findByEmail(email);
        if (start.equals("vrai")) {

            initialiserService.initialiser(u);

        } else {
            u.setEnCoursDeJeu(1);
            utilisateurService.save(u);
        }

        resp.sendRedirect("plateforme");

    }

}

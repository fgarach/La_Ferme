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
import laferme.service.UtilisateurCrudService;
import laferme.spring.AutowireServlet;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends AutowireServlet {

    @Autowired
    private UtilisateurCrudService utilisateurService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("_CSS.jsp").include(req, resp);
        req.getRequestDispatcher("_HEADER.jsp").include(req, resp);

        try {
            String email = req.getSession().getAttribute("email").toString();
            Utilisateur u = utilisateurService.findByEmail(email);
            req.setAttribute("utilisateur", u);

        } catch (Exception e) {
        }
        req.getRequestDispatcher("_HOME.jsp").include(req, resp);
        req.getRequestDispatcher("_FOOTER.jsp").include(req, resp);
    }

}

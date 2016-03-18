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
import laferme.exception.LoginNonExistant;
import laferme.exception.MotDePasseIncorrect;
import laferme.service.ConnexionService;
import laferme.spring.AutowireServlet;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends AutowireServlet {

    @Autowired
    ConnexionService connexionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("_CSS.jsp").include(req, resp);
        req.getRequestDispatcher("_HEADER.jsp").include(req, resp);
        req.getRequestDispatcher("Formulaire_login.jsp").include(req, resp);
        req.getRequestDispatcher("_FOOTER.jsp").include(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String mdp = req.getParameter("mdp");

        try {
            
            connexionService.connexion(email, mdp);
            req.getSession().setAttribute("email", email);
            resp.sendRedirect("home");
        } catch (LoginNonExistant ex) {
            String information = "Votre login n'est pas reconnu";
            req.setAttribute("info", information);
            login(req, resp);
        } catch (MotDePasseIncorrect ex) {
            String information = "Votre mot de passe est incorrect";
            req.setAttribute("info", information);
            login(req, resp);
        }

    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("_CSS.jsp").include(req, resp);
        req.getRequestDispatcher("_HEADER.jsp").include(req, resp);
        req.getRequestDispatcher("Formulaire_login.jsp").include(req, resp);
        req.getRequestDispatcher("_FOOTER.jsp").include(req, resp);
    }

}

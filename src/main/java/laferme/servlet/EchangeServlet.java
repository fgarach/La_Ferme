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
import laferme.enumeration.TypeRessource;
import laferme.service.RessourceService;
import laferme.service.UtilisateurCrudService;
import laferme.spring.AutowireServlet;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author admin
 */
@WebServlet(name = "EchangeServlet", urlPatterns = {"/echange"})
public class EchangeServlet extends AutowireServlet {
    
    @Autowired
    RessourceService ressourceService;
    
    @Autowired
    UtilisateurCrudService utilisateurCrudService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String option = "echange";
        req.setAttribute("option", option);
        req.getRequestDispatcher("plateforme").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String email = (String) req.getSession().getAttribute("email");
        Utilisateur u = utilisateurCrudService.findByEmail(email);
        String ressourcePossedees = req.getParameter("ressechange");
        String ressourceVoulues = req.getParameter("resscontre");
        ressourceService.echange(ressourcePossedees, ressourceVoulues, u);
        resp.sendRedirect("plateforme");
        
    }
    
    


}
